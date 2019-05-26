package com.okex.trande.serviceI;

public interface OkexGridLoopStatusServiceI {
	void execute(String currency);
	void updateN9999Order(String currency);
}
