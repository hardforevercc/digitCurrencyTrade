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
	private static final String BTC_USDT = "btc_usdt";
	private static final String ADA_BTC = "ada_usdt";
	private static final String BUY = "buy";
	private static final String SELL = "sell";
	private static final String MARKETBUY = "buy_market";
	private static final String MARKETSELL = "sell_market";
	private static BigDecimal currprice;
	private static BigDecimal amount;
	private static BigDecimal myUsdt;
	private static BigDecimal myAda;
	private static BigDecimal perprice;
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
		 * 1.计算当前账户持币百分比 当前算法按单位为usdt换算
		 * 2.判断当前持仓情况选择不同策略
		 *		若当前账户持仓量>50%
		 */
		BigDecimal adaPercent = myAda.divide(myAda.add(myUsdt));
		if(adaPercent.compareTo(new BigDecimal("0.5"))>0) {
			log.info("当前持仓量>50");
			if(currprice.compareTo(new BigDecimal("0.11")) <0 ) {
				//若买入价>当前价 继续买入
				if(perprice.compareTo(currprice) <0) {
					//计算当前价格和 0.11零界点的百分比 价格比0.11越小 买入量越大 直到[买入金额 - 手续费金额 <=0]停止买入
					BigDecimal curr = new BigDecimal("0.11").subtract(currprice).divide(new BigDecimal("0.11"));
					amount = myUsdt.multiply(curr);
				}else {
					//若买入价>当前价 继续买入
					
				}
				
			}else if(currprice.compareTo(new BigDecimal("0.15")) >0) {
				
			}else {
				
			}
		/**
		 * 若当前持仓量<50%
		 */
		}else {
			
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
