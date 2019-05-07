package com.okex.trande.serviceI;

public interface OkexGridByDayServiceI {
	void execute(String currency);
	void queryDealSts(String currency,String sts);
}
