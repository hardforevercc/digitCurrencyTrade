package com.okex.mybatis.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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
	int upateTo9999(@Param("orderId") String orderId,@Param("currency") String currency);
	int selectBuyOrderNum(@Param("orderId") String orderId);
	int selectSellOrderNum(@Param("orderId") String orderId);
	double selectOnchangePercent(@Param("buyOnePrice") BigDecimal buyOnePrice,@Param("currency") String currency);
	BigDecimal selectMinFilledOpen(@Param("currency") String currency);
	BigDecimal selectMinFilledOpenSell(@Param("currency") String currency);
	Map<String,BigDecimal> selectDealChangeConfig(@Param("currency") String currency,@Param("price") String price);
	
	int selectDealChangeRecord(@Param("currency") String currency,@Param("price") String price,@Param("type") String type);
	
	int selectPriceId(@Param("currency") String currency,@Param("price") String price);
}
