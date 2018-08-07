package com.okex.bean;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class DepthBean {
	private String date;
	private String date_ms;
	private BigDecimal amount;
	private BigDecimal price;
	private String type;
	private String tid;
}
