package com.okex.bean;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * 单笔报文交易实体类
 * @author 10435
 *
 */
@Getter
@Setter
public class TradeSingleBean {
	private String symbol;//币对如ltc_btc|
	//买卖类型：限价单(buy/sell)
	//买卖类型：限价单(buy/sell)
	//市价单(buy_market/sell_market)
	//市价单(buy_market/sell_market)
	private BigDecimal price;//下单价格 市价卖单不传price|
	private BigDecimal amount;//交易数量 市价买单不传amount,市价买单需传price作为买入总金额
	private String type;
	
	
}
