package com.okex.trande.serviceI;

import java.math.BigDecimal;

public interface OkexDealChangeServiceI {
	void execute(String currency);
	BigDecimal getAvaliable(String currency);
	void loopDeal(String curreny);
	void updateOrder(String currenccy);
}
