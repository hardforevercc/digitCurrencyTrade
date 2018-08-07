package com.okex.trande.serviceI;

public interface OkexPublicServiceI {
	public String getTicker(String currency);
	public String getMarket(String currecy);
	public String getDepth(String currecy);
}
