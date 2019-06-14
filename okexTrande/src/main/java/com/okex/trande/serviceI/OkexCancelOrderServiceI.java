package com.okex.trande.serviceI;

public interface OkexCancelOrderServiceI {
	void batchCancel(String currency);
	void cancel(String currency);
}
