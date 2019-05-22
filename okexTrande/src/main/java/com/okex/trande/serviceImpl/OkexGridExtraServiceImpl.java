package com.okex.trande.serviceImpl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.okcoin.commons.okex.open.api.bean.spot.param.PlaceOrderParam;
import com.okcoin.commons.okex.open.api.bean.spot.result.OrderResult;
import com.okcoin.commons.okex.open.api.service.spot.SpotOrderAPIServive;
import com.okcoin.commons.okex.open.api.service.spot.SpotProductAPIService;
import com.okex.mybatis.dao.OkexGridPlanMapper;
import com.okex.mybatis.model.OkexGridPlan;
import com.okex.mybatis.model.OkexGridPlanExample;
import com.okex.trande.serviceI.OkexGridExtraServiceI;
import com.okex.trande.utils.CommonUtils;

import lombok.extern.slf4j.Slf4j;
/**
 * 网格交易法额外补充
 * @author cy
 *
 */
@Slf4j
@Service("okexGridExtraService")
public class OkexGridExtraServiceImpl implements OkexGridExtraServiceI {
	@Autowired OkexGridPlanMapper gridPlanMapper;
	@Autowired SpotProductAPIService spotProductAPIService;
	@Autowired SpotOrderAPIServive spotOrderApiService;
	
	private static int DOWN = BigDecimal.ROUND_DOWN;
	private static BigDecimal buyPrice,buyAmount;
	@Override
	public void execute(String currency) {
		BigDecimal buyOnePrice = null;
		
		try {
			OkexGridPlanExample planExam = new OkexGridPlanExample();
			planExam.createCriteria().andBuystsEqualTo("filled").
			andSellstsEqualTo("filled").andCurrencyEqualTo(currency);
			
			List<OkexGridPlan> planGridList = gridPlanMapper.selectByExample(planExam);
			PlaceOrderParam orderParam = null;
			OkexGridPlan plan = null;
			if(planGridList == null || planGridList.size() <0) {
				log.info("无成交交易,不执行补充计划");
				return;
			}
			for(OkexGridPlan gridPlan:planGridList) {
				
				if(null != buyOnePrice && currency.equals(gridPlan.getCurrency())) {
					buyOnePrice = buyOnePrice.multiply(BigDecimal.ONE.
							subtract(new BigDecimal("0.02")));
				}else {
					//获取买一价
					buyOnePrice = new BigDecimal(spotProductAPIService.
							getTickerByProductId(gridPlan.getCurrency()).getBest_bid());
				}
				
				plan = packGridPlan(gridPlan,buyOnePrice);			
				orderParam = packBuyParam(plan);
				log.info("执行计划订单:"+JSONObject.toJSONString(orderParam));
				OrderResult result = spotOrderApiService.addOrder(orderParam);
				if("-1".equals(result.getOrder_id().toString())) {
					log.info("交易失败: "+result.getClient_oid());
					return;
				}
				plan.setBuyid(result.getOrder_id().toString());
				plan.setBuysts("open");
				gridPlanMapper.insert(plan);
				
			}
		}catch(Exception e) {
			log.error("额外补充网格交易执行异常",e);
		}
		

	}
	private static OkexGridPlan packGridPlan(OkexGridPlan gridPlan,BigDecimal buyOnePrice) {
		OkexGridPlan plan = new OkexGridPlan();
		buyPrice = buyOnePrice.multiply(BigDecimal.ONE.subtract(BigDecimal.valueOf(0.005)));
		buyAmount = gridPlan.getActbuyprice().divide(buyPrice,BigDecimal.ROUND_DOWN).setScale(4, DOWN);
		String currencyType  = gridPlan.getCurrency().replaceAll("-", "");
		plan.setBuyprice(buyPrice);
		plan.setAmount(buyAmount);
		plan.setBuyid(currencyType+CommonUtils.getTime()+"b");
		plan.setBuysts("00");
		plan.setCreateDate(new Date());
		plan.setSellprice(buyOnePrice.multiply(BigDecimal.ONE.add(BigDecimal.valueOf(0.01))));
		plan.setSellid(currencyType+CommonUtils.getTime()+"s");
		plan.setSellsts("00");
		return plan;
	}
	
	private static PlaceOrderParam packBuyParam(OkexGridPlan plan) {
		PlaceOrderParam orderParam = new PlaceOrderParam();
		orderParam.setClient_oid(plan.getBuyid());
		orderParam.setType("limit");
		orderParam.setPrice(plan.getBuyprice().toString());
		orderParam.setSize(plan.getAmount().toString());
		orderParam.setSide("buy");			
		orderParam.setInstrument_id(plan.getCurrency());
		
		return orderParam;
	}

}
