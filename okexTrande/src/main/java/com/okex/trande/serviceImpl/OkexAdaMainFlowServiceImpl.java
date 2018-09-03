package com.okex.trande.serviceImpl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.okex.bean.TickerBean;
import com.okex.bean.TradeSingleBean;
import com.okex.mybatis.dao.OkexExtMapper;
import com.okex.mybatis.dao.OkexTradeRecordMapper;
import com.okex.mybatis.model.OkexTradeRecord;
import com.okex.trande.serviceI.OkexAdaMainFlowServiceI;
import com.okex.trande.serviceI.OkexPrivateServiceI;
import com.okex.trande.serviceI.OkexPublicServiceI;
import com.okex.trande.utils.CommonUtils;
import com.okex.trande.utils.OkexTradeUtils;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service("okexAdaMainFlowService")
public class OkexAdaMainFlowServiceImpl implements OkexAdaMainFlowServiceI{
	@Autowired
	OkexPublicServiceI okexPublicService;
	@Autowired
	OkexPrivateServiceI okexPrivateService;
	@Autowired
	OkexExtMapper okexExtDao;
	@Autowired
	OkexTradeRecordMapper okexTradeRecordDao;
	private static final String BTC_USDT = "btc_usdt";
	private static final String ADA_BTC = "ada_usdt";
	private static final String BUY = "buy";
	private static final String SELL = "sell";
	private static String type;
	private static final String MARKETBUY = "buy_market";
	private static final String MARKETSELL = "sell_market";
	private static BigDecimal currprice;//当前价格
	private static BigDecimal currAda;//当前价格
	private static BigDecimal amount;
	private static BigDecimal myTradeAmt;
	private static BigDecimal myUsdt;
	private static BigDecimal myAda;
	private static final BigDecimal RULELEASTAMT = new BigDecimal("0.11");
	private static final BigDecimal RULEMAXAMT = new BigDecimal("0.15");
	private static final BigDecimal FEE = new BigDecimal("0.37").divide(new BigDecimal("100"));
	private static BigDecimal lastBuyPrice;
	private static int i = 0;
	private static Map<String,String> tradeMap = new HashMap<String,String>();
	/**
	 * 基于ADA稳定的价格 制定ADA就交易策略
	 * @throws Exception 
	 */
	
	public void execute() throws Exception {
		while(true) {
			runAda();
			Thread.sleep(400);
		}
		
		
	}
	public void runAda() {
		log.info("[***************OKEX TRADING START******************]");
		try {
			long startTime = System.currentTimeMillis();
			log.info("step 1: get the price of ada&btc statrt->"+CommonUtils.getTime());
			String btcTickerResp = okexPublicService.getTicker(BTC_USDT);
			TickerBean btcTicker = OkexTradeUtils.dealTicker(btcTickerResp);
			if(null == btcTicker) {
				log.error("query the price of btc is empty->"+CommonUtils.getTime());
				return;
			}
			currprice = btcTicker.getTicker().getLast();
			log.info("[$$$$$$$$$$$][the last price of btc_usdt = "+currprice);
			String adaTickerResp = okexPublicService.getTicker(ADA_BTC);
			TickerBean adaTicker = OkexTradeUtils.dealTicker(adaTickerResp);
			currAda = adaTicker.getTicker().getLast();
			log.info("[$$$$$$$$$$$][the last price of ada_usdt = "+currAda);
			log.info("step 1: get the price of ada&btc end->"+CommonUtils.getTime());
			/**
			 * 等待数据完善根据数据信息修改交易规则
			 * 获取最近时间段的交易信息：
			 * 平均成交量
			 * 最大买入 卖出价格
			 * 
			 */
			log.info("step 2: get the userinfo of ada&btc end->"+CommonUtils.getTime());
			String balance = okexPrivateService.getBalance();
			Map<String,String> freeAcMap = OkexTradeUtils.getFreeAc(balance, null);
			myAda = new BigDecimal(freeAcMap.get("adaAc"));
			myUsdt = new BigDecimal(freeAcMap.get("usdtAc"));
			log.info("[$$$$$$$$$$$][myAda="+myAda+",myUsdt="+myUsdt+"]");
			if(null ==lastBuyPrice || lastBuyPrice.equals(BigDecimal.ZERO)) {
				lastBuyPrice = okexExtDao.selectLastBuyPrice();
				//perprice = lastBuyPrice;
			}
			Map<String,String> orderMap = new HashMap<String,String>();
			if(lastBuyPrice.compareTo(currAda) == 0) {
				log.info("第{"+i+"}次交易失败,lastBuyPrice=currAda["+lastBuyPrice);
				return;
			}
			/**
			 * 挂单手续费0.15%;吃单手续费0.2%
			 * 若当前持仓量为0;则按价格区间买入
			 * 1.计算当前账户持币百分比 当前算法按单位为usdt换算
			 * 
			 */
			try {
				BigDecimal avgPrice = okexExtDao.selectMyAvgPriceRecord();
				log.info("avgPrice:"+avgPrice);
			}catch(Exception e) {
				log.error("查询数据库异常",e);
			}
			/**
			 * 当前持仓价格 = sum(历史买入价格*历史买入量)/sum(历史买入量)
			 */
			if(myAda.compareTo(BigDecimal.ONE) < 1) {
				log.info("第{"+i+"}次交易,myAda持有量<1,视为空仓,持仓价格设置为0");
				lastBuyPrice = BigDecimal.ZERO;
			}
			if(myUsdt.compareTo(BigDecimal.ONE) <0) {
				log.info("第{"+i+"}次交易,myUsdt持有量<1,视为满仓");
				BigDecimal earnper = currAda.subtract(lastBuyPrice).divide(currAda,BigDecimal.ROUND_HALF_DOWN);
				if( earnper.compareTo(FEE) > 0){						
					type = "sell";
					myTradeAmt = earnper.multiply(myAda).divide(currAda,BigDecimal.ROUND_HALF_DOWN);
					if(myTradeAmt.compareTo(BigDecimal.ONE) <0) {
						myTradeAmt = BigDecimal.ONE;
					}
					orderMap.put("type",type);
					orderMap.put("amount",myTradeAmt.toString());
					orderMap.put("symbol",ADA_BTC );
					orderMap.put("price", currAda.toString());
					log.info("单笔订单请求:"+JSONObject.toJSONString(orderMap));		
					boolean flag = exeTrade(orderMap);
					
					if(flag) {
						lastBuyPrice = currAda;
						insertIntoRecord(orderMap,flag);
					}else {
						log.info("交易失败第{"+i+"}次");
					}
					i++;
					return;
				}else {
					log.info("第{"+i+"}次交易,myUsdt持有量<1,满仓，执行卖出失败");
					return;
				}
				
				
			}
			
			BigDecimal adaPercent = myAda.divide(myAda.add(myUsdt),BigDecimal.ROUND_HALF_DOWN);
			log.info("当前ada持仓比:"+adaPercent);
			//若当前ada价格小于0.11
			if(currAda.compareTo(RULELEASTAMT) <0 ) {
				if(myAda.compareTo(BigDecimal.ONE) < 0) {
					BigDecimal buyAmount = currAda.subtract(RULEMAXAMT).abs().divide(RULELEASTAMT,BigDecimal.ROUND_HALF_DOWN);
					myTradeAmt = myUsdt.multiply(buyAmount).divide(currAda,BigDecimal.ROUND_HALF_DOWN);
					type = "buy";
					log.info("当前持仓量为0,首次买入量为:"+buyAmount);
					
				}else {
					//若持有价格>当前价 继续买入
					if(lastBuyPrice.compareTo(currAda) >0) {
						//计算当前价格和 0.11零界点的百分比 价格比0.11越小 买入量越大 直到[买入金额 - 手续费金额 <=0]停止买入
						BigDecimal curr = RULELEASTAMT.subtract(currAda).divide(RULELEASTAMT,BigDecimal.ROUND_HALF_DOWN);
						myTradeAmt = curr.multiply(myUsdt).divide(currAda,BigDecimal.ROUND_HALF_DOWN); 
						if(myTradeAmt.compareTo(BigDecimal.ONE) <0) {
							myTradeAmt = BigDecimal.ONE;
						}
						type = "buy";
					}else {
						/** 
						 * todo 
						 * 计算卖出比例:卖出金额为 当前持仓量*溢价量
						 * 执行卖出操作
						 */
						BigDecimal earnper = currAda.subtract(lastBuyPrice).divide(currAda,BigDecimal.ROUND_HALF_DOWN);
						if( earnper.compareTo(FEE) > 0){						
							type = "sell";
							myTradeAmt = earnper.multiply(myAda).divide(currAda,BigDecimal.ROUND_HALF_DOWN);
							if(myTradeAmt.compareTo(BigDecimal.ONE) <0) {
								myTradeAmt = BigDecimal.ONE;
							}
						}			
					}
				}
				
			/**
			 * 若当前价格>0.15,则无买入行为	
			 */
			}else if(currAda.compareTo(RULEMAXAMT) > 0 ) {
				BigDecimal curr = currAda.subtract(lastBuyPrice).divide(currAda,BigDecimal.ROUND_HALF_DOWN);
				if(curr.compareTo(new BigDecimal("0.01")) >0) {
					//执行卖出
					amount = curr.multiply(myUsdt).divide(currAda,BigDecimal.ROUND_HALF_DOWN);
					if(myTradeAmt.compareTo(BigDecimal.ONE) <0) {
						myTradeAmt = BigDecimal.ONE;
					}
					type = "sell";
				}
			}else {
				if(myAda.compareTo(BigDecimal.ZERO) ==0) {
					BigDecimal buyAmount = lastBuyPrice.subtract(RULEMAXAMT).abs().divide(RULELEASTAMT,BigDecimal.ROUND_HALF_DOWN);
					myTradeAmt = myUsdt.multiply(buyAmount);
					if(myTradeAmt.compareTo(BigDecimal.ONE) <0) {
						myTradeAmt = BigDecimal.ONE;
					}
					
					type = "buy";
					log.info("当前持仓量为0,首次买入量为:"+myTradeAmt);
					
				}else {
					boolean flag = lastBuyPrice.subtract(currAda).compareTo(BigDecimal.ZERO) > 0 ? true: false;
					BigDecimal curr = lastBuyPrice.subtract(currAda).divide(RULEMAXAMT,BigDecimal.ROUND_HALF_DOWN).abs();
					if(flag) {
						type = "buy";
						myTradeAmt = curr.multiply(myUsdt).divide(currAda,BigDecimal.ROUND_HALF_DOWN);
						if(myTradeAmt.compareTo(BigDecimal.ONE) <0) {
							myTradeAmt = BigDecimal.ONE;
						}
					}else {
						myTradeAmt = curr.multiply(myUsdt).divide(currAda,BigDecimal.ROUND_HALF_DOWN);
						type = "sell";
						if(myTradeAmt.compareTo(BigDecimal.ONE) <0) {
							myTradeAmt = BigDecimal.ONE;
						}
					}
				}
				
			}
			if(null == myTradeAmt) {
				log.info("第{"+i+"}次未交易");
				return;
				//myTradeAmt = BigDecimal.ZERO;
			}
			
			orderMap.put("type",type);
			orderMap.put("amount",myTradeAmt.toString());
			orderMap.put("symbol",ADA_BTC );
			orderMap.put("price", currAda.toString());
			log.info("单笔订单请求:"+JSONObject.toJSONString(orderMap));
				
			
			boolean flag = exeTrade(orderMap);
			
			if(flag) {
				lastBuyPrice = currAda;
				insertIntoRecord(orderMap,flag);
			}else {
				log.info("交易失败第{"+i+"}次");
			}
			i++;
			log.info("第{"+i+"}持仓价格为:"+lastBuyPrice);
			long endTime = System.currentTimeMillis();
			long time = endTime - startTime;
			log.info("第{"+i+"}次执行成功,耗时:"+time);
			//判断是否有挂单
			
			/**
			 * 制定规则：
			 * 强规则:
			 * 买入: $ada <= 0.11 购入当前资金50%  在此价格基础上:每下跌1% 买入当前资金2%
			 * 卖出: $ada >= 0.15 卖出当前资金50%  在此价格基础上:每上涨1% 卖出当前资金2%
			 * 弱规则:
			 * $ada >= 0.11 && $ada <= 0.15
			 * 在此区间内随机买入，买入量>=(当前闲置资金)(0-50%)区间内
			 * 若价格大于或小于此区间内买入价格5% 按波动率买入或卖出
			 *  
			 */
			
		}catch(Exception e) {
			log.error("第{"+i+"}次执行成功出现异常",e);
		}
		
	}
	public void exeTradeOrder(String type,String amount,String price) {
		tradeMap.put("type",type);
		tradeMap.put("amount",amount.toString());
		tradeMap.put("symbol",ADA_BTC );
		tradeMap.put("price", price.toString());
		boolean flag = exeTrade(tradeMap);
		
		if(flag) {
			lastBuyPrice = currAda;
			insertIntoRecord(tradeMap,flag);
		}else {
			log.info("交易失败第{"+i+"}次");
		}
	}
		
	private void insertIntoRecord(Map<String,String> map,boolean flag) {
		OkexTradeRecord record = new OkexTradeRecord();
		BigDecimal amount = new BigDecimal(map.get("amount"));
		BigDecimal price = new BigDecimal(map.get("price"));
		if(flag) {			
			record.setIsok("Y");			
		}else {		
			record.setIsok("N");
		}
		record.setAmt(price.multiply(amount));
		record.setCreateDate(new Date());
		record.setCurprice(price);
		record.setCuramount(amount);
		record.setType(map.get("type").toString());
		okexTradeRecordDao.insert(record);
	}
	/**
	 * 执行订单
	 * @param orderMap
	 * @return
	 */
	private boolean exeTrade(Map<String,String> orderMap) {
		log.info("订单信息:"+JSONObject.toJSONString(orderMap));
		String orderResp = okexPrivateService.exeOrder(orderMap);
		log.info("订单结果:"+orderResp);
		if(null == orderResp) {
			log.error("系统异常,查询结果为空");
		}
		if(JSONObject.parseObject(orderResp).getBooleanValue("result")) {
			return true;
		}else{
			return false;
		}
	}
}
