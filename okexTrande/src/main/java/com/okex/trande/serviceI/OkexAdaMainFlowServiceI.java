package com.okex.trande.serviceI;

public interface OkexAdaMainFlowServiceI {
	public void execute() throws Exception;
	public void exeTradeOrder(String type,String amount,String price);
}
