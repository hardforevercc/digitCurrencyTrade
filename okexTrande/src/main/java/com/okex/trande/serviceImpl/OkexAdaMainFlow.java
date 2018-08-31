package com.okex.trande.serviceImpl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import com.okex.trande.serviceI.OkexAdaMainFlowServiceI;

public class OkexAdaMainFlow {
	private static final String BTC_USDT = "btc_usdt";
	private static final String ADA_BTC = "ada_usdt";
	private static final String BUY = "buy";
	private static final String SELL = "sell";
	private static String type;
	private static final String MARKETBUY = "buy_market";
	private static final String MARKETSELL = "sell_market";
	private static BigDecimal currprice;//当前价格
	private static BigDecimal curAda;//当前Ada价格
	private static BigDecimal amount;
	private static BigDecimal myTradeAmt;
	private static BigDecimal myUsdt;
	private static BigDecimal myAda;
	private static final BigDecimal RULELEASTAMT = new BigDecimal("0.11");
	private static final BigDecimal RULEMAXAMT = new BigDecimal("0.15");
	private static final BigDecimal FEE = new BigDecimal("0.37").divide(new BigDecimal("100"));
	private static BigDecimal lastBuyPrice;
	private static int i = 0;
	@Autowired
    OkexAdaMainFlowServiceI okexAdaMainFlowService;
	
	private void  execute() {
		//查询ada价格
		//查询usdt价格
		if(lastBuyPrice == null) {
			//lastBuyPrice = 最后一条买入或卖出记录
		}
		if(myAda.compareTo(BigDecimal.ONE) < 0) {
			//视为空仓
		}
		if(myUsdt.compareTo(BigDecimal.ONE) < 0) {
			//视为满仓
		}
		BigDecimal holdPercent =myAda.multiply(curAda).divide(myAda.multiply(curAda).add(myUsdt),BigDecimal.ROUND_DOWN); 
		BigDecimal myUsdtToAda = myUsdt.divide(curAda,BigDecimal.ROUND_DOWN);
		BigDecimal buyAndsellPercent = new BigDecimal("0.1");
		boolean flag = lastBuyPrice.subtract(curAda.multiply(BigDecimal.ONE.add(FEE))).compareTo(BigDecimal.ZERO) >= 0?true:false;
		if(lastBuyPrice.subtract(curAda).compareTo(BigDecimal.ZERO) >= 0) {
			flag = lastBuyPrice.subtract(curAda.multiply(BigDecimal.ONE.add(FEE))).compareTo(BigDecimal.ZERO) >= 0?true:false;
		}else {
			flag = lastBuyPrice.subtract(curAda.multiply(BigDecimal.ONE.add(FEE))).compareTo(BigDecimal.ZERO) >= 0?true:false;
		}	
		//当前价格小于0.11
		if(curAda.compareTo(RULELEASTAMT) < 0 ) {
			if(curAda.compareTo(new BigDecimal("0.1")) <=0 ) {
				if(holdPercent.compareTo(new BigDecimal("0.4")) <= 0) {
					//only buy
					buyAndsellPercent = new BigDecimal("0.4").subtract(holdPercent);
					okexAdaMainFlowService.exeTradeOrder(BUY,buyAndsellPercent.toString(),curAda.toString());
					return;
				}else {
					
					if(flag) {
						
					}else {
						
					}					
				}
			}else if(curAda.compareTo(new BigDecimal("0.09")) <=0){
				if
				(holdPercent.compareTo(new BigDecimal("0.5")) <= 0) {
					//only buy
				}else {
					//buy
				}
			}else {
				if(holdPercent.compareTo(new BigDecimal("0.3")) <= 0) {
					//only buy
				}
			}
		}
		//当前价格大于0.15
		else if(curAda.compareTo(RULEMAXAMT) > 0 ) {
			if(holdPercent.compareTo(new BigDecimal("0.1")) <= 0) {
				//only buy
			}
		}
		//当前价格大于等于0.11,小于等于0.15
		else {
			if(holdPercent.compareTo(new BigDecimal("0.3")) <= 0) {
				//only buy
			}
		}
		
	}
}
