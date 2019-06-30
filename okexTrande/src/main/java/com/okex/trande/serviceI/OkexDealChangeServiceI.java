package com.okex.trande.serviceI;

import java.math.BigDecimal;
import java.util.List;

import com.okex.mybatis.model.OkexDealChangePlan;

public interface OkexDealChangeServiceI {
	void execute(String currency);
	BigDecimal getAvaliable(String currency);
	void loopDeal(String curreny);
	void updateOrder(String currenccy);
	void updateOrderById(List<OkexDealChangePlan> dealAPlanList,String currenccy);
}
