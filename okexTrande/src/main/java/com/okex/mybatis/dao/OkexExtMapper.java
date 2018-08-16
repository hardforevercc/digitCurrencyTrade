package com.okex.mybatis.dao;

import java.math.BigDecimal;

import com.okex.mybatis.model.OkexTradeRecord;

public interface OkexExtMapper {
	public OkexTradeRecord selectLastedBuyRecord();
	public BigDecimal selectMyAvgPriceRecord();
	public BigDecimal selectLastBuyPrice();
	public BigDecimal selectCostUsdtByAda();
}
