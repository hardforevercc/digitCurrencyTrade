package com.okex.bean;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TradeOrdersData {
	private BigDecimal price;
	private BigDecimal amount;
	private String type;//sell buy
}
