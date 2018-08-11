package com.okex.trande.serviceImpl;

import java.math.BigDecimal;
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
	private static BigDecimal amount;
	private static BigDecimal myTradeAmt;
	private static BigDecimal myUsdt;
	private static BigDecimal myAda;
	private static BigDecimal perprice;//持仓价格
	private static final BigDecimal RULELEASTAMT = new BigDecimal("0.11");
	private static final BigDecimal RULEMAXAMT = new BigDecimal("0.15");
	private static final BigDecimal FEE = new BigDecimal("0.35").divide(new BigDecimal("100"));
	/**
	 * 基于ADA稳定的价格 制定ADA就交易策略
	 * @throws Exception 
	 */
	public String execute() throws Exception {		
		log.info("[***************OKEX TRADING START******************]");
		log.info("step 1: get the price of ada&btc statrt->"+CommonUtils.getTime());
		String btcTickerResp = okexPublicService.getTicker(BTC_USDT);
		TickerBean btcTicker = OkexTradeUtils.dealTicker(btcTickerResp);
		if(null == btcTicker) {
			throw new Exception("query the price of btc is empty->"+CommonUtils.getTime());
		}
		currprice = btcTicker.getTicker().getLast();
		log.info("the last price of btc_usdt = "+currprice);
		String adaTickerResp = okexPublicService.getTicker(ADA_BTC);
		TickerBean adaTicker = OkexTradeUtils.dealTicker(adaTickerResp);
		log.info("the last price of ada_usdt = "+adaTicker.getTicker().getLast());
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
		
		Map<String,String> orderMap = new HashMap<String,String>();
		orderMap.put("type",BUY);
		orderMap.put("amount", "100");
		orderMap.put("symbol",ADA_BTC );
		orderMap.put("price", currprice.toString());
		String orderResp = okexPrivateService.exeOrder(orderMap);
		log.info("订单结果:"+orderResp);
		/**
		 * 挂单手续费0.15%;吃单手续费0.2%
		 * 若当前持仓量为0;则按价格区间买入
		 * 1.计算当前账户持币百分比 当前算法按单位为usdt换算
		 * 
		 */
		try {
			BigDecimal price = okexExtDao.selectMyAvgPriceRecord();
			log.info("price:"+price);
		}catch(Exception e) {
			log.error("查询数据库异常",e);
		}
		
		if(myAda.compareTo(BigDecimal.ZERO) ==0) {
			BigDecimal buyAmount = currprice.subtract(RULEMAXAMT).abs().divide(RULELEASTAMT);
			myUsdt.multiply(buyAmount);
			return null;
		}
		log.info("记录当前持仓价格");
		/**
		 * 当前持仓价格 = sum(历史买入价格*历史买入量)/sum(历史买入量)
		 */
		BigDecimal myAvgPrice = null;//= okexExtDao.selectMyAvgPriceRecord();
		
		
		BigDecimal adaPercent = myAda.divide(myAda.add(myUsdt));
		log.info("当前ada持仓比:"+adaPercent);
		if(currprice.compareTo(RULELEASTAMT) <0 ) {
			//若买入价>当前价 继续买入
			if(perprice.compareTo(currprice) <0) {
				//计算当前价格和 0.11零界点的百分比 价格比0.11越小 买入量越大 直到[买入金额 - 手续费金额 <=0]停止买入
				BigDecimal curr = RULELEASTAMT.subtract(currprice).divide(RULELEASTAMT);
				myTradeAmt = curr.multiply(myUsdt); 
				type = "buy";
			}else {
				//若买入价<当前价 卖出
				//溢价比例
				BigDecimal earnper = currprice.subtract(perprice).divide(currprice);
				if(currprice.compareTo(RULELEASTAMT) >0 && earnper.compareTo(FEE) > 0){
					/** 
					 * todo 
					 * 计算卖出比例:卖出金额为 当前持仓量*溢价量
					 * 执行卖出操作
					 */
					type = "sell";
					myTradeAmt = earnper.multiply(myAda);
				}					
			}
		/**
		 * 若当前价格>0.15,则无买入行为	
		 */
		}else if(currprice.compareTo(RULEMAXAMT) > 0 ) {
			BigDecimal curr = currprice.subtract(perprice).divide(currprice);
			if(curr.compareTo(new BigDecimal("0.01")) >0) {
				//执行卖出
				amount = curr.multiply(myUsdt);
			}
		}else {
			boolean flag = myAvgPrice.subtract(currprice).compareTo(BigDecimal.ZERO) > 0 ? true: false;
			BigDecimal curr = myAvgPrice.subtract(currprice).divide(RULEMAXAMT).abs();
			if(flag) {
				type = "buy";
				myTradeAmt = curr.multiply(myUsdt);
				
			}else {
				myTradeAmt = curr.multiply(myUsdt);
				type = "sell";
			}
		}
		
//		TradeSingleBean order = new TradeSingleBean();
		
//		order.setPrice(new BigDecimal("100.00"));
//		order.setSymbol(ADA_BTC);
//		order.setType(MARKETBUY);
		
		
		
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
		return btcTickerResp;
	}
}
