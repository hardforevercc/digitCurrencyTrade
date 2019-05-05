package com.okex.trande.enums;

public enum OkexTradeEnums {
	USERINFOURL("https://www.okex.me/api/spot/v3/accounts","获取用户信息"),
	TRADEURL("https://www.okex.me/api/v1/trade.do","单笔订单下单"),
	TRADEBATCHURL("https://www.okex.me/api/v1/batch_trade.do","批量订单下单"),
	CANCELORDERURL("https://www.okex.me/api/v1/cancel_order.do","撤销订单"),
	ORDERINFOURL("https://www.okex.me/api/v1/order_info.do","用户订单信息"),
	ORDERSINFOURL("https://www.okex.me/api/v1/orders_info.do","批量获取用户订单信息"),
	HISORDERSURL("https://www.okex.me/api/v1/order_history.do","用户近2天订单信息"),
	UNSUCESSURL("https://www.okex.me/api/spot/v3/orders_pending","交易未成功订单")
	;
	
	private String url;
	private String desc;
	private OkexTradeEnums(String url, String desc) {
		this.url = url;
		this.desc = desc;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	
}
