package com.okex.bean;

public class TradeBatchBean {
	private String symbol;
	private String type;//type|String|否|买卖类型：限价单(buy/sell)|
	private TradeOrdersData orders_data;
	//|orders_data|String(格式\[{price:3,amount:5,type:'sell'},{price:3,amount:3,type:'buy'}])|是|最大下单量为5， price和amount参数参考trade接口中的说明，最终买卖类型由orders_data 中type 为准，如orders_data不设定type 则由上面type设置为准。|
}
