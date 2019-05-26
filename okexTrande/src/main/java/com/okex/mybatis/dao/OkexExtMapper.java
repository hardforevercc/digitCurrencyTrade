package com.okex.mybatis.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.okex.mybatis.model.OkexGridConfig;
import com.okex.mybatis.model.OkexGridPlan;
import com.okex.mybatis.model.OkexTradeRecord;

public interface OkexExtMapper {
	OkexTradeRecord selectLastedBuyRecord();
	BigDecimal selectMyAvgPriceRecord();
	BigDecimal selectLastBuyPrice();
	BigDecimal selectCostUsdtByAda();
	OkexGridConfig selectOneGridConfig(String currency);
	int insertGridPlanList(List<OkexGridPlan> gridList);
	int selectLeftPlan(String currency);
	int upateTo9999(@Param("orderId") String orderId);
}
