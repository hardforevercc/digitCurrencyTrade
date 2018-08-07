package com.okex.bean;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TickerDetail {
	private BigDecimal high;
	private BigDecimal vol;
	private BigDecimal last;
	private BigDecimal low;
	private BigDecimal buy;
	private BigDecimal sell;
}
