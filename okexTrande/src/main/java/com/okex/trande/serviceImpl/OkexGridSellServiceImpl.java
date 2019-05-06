package com.okex.trande.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okcoin.commons.okex.open.api.bean.spot.param.PlaceOrderParam;
import com.okcoin.commons.okex.open.api.bean.spot.result.OrderResult;
import com.okcoin.commons.okex.open.api.service.spot.SpotOrderAPIServive;
import com.okex.mybatis.dao.OkexGridPlanMapper;
import com.okex.mybatis.model.OkexGridPlan;
import com.okex.mybatis.model.OkexGridPlanExample;
import com.okex.trande.serviceI.OkexGridSellServiceI;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service("okexGridSellService")
public class OkexGridSellServiceImpl implements OkexGridSellServiceI {

	@Autowired OkexGridPlanMapper planMapper;
	@Autowired SpotOrderAPIServive spotOrderApiService;
	
	@Override
	public void execute(String currency) {
		//获取买入状态为完全成交的订单
		OkexGridPlanExample sellExample = new OkexGridPlanExample();
		sellExample.createCriteria().andBuystsEqualTo("filled").andSellorderidIsNull()
		.andCurrencyEqualTo(currency);
		List<PlaceOrderParam> sellList = exeSell(planMapper,sellExample);
		if(null == sellList) {
			return;
		}
		Map<String, List<OrderResult>> resultMap =  spotOrderApiService.addOrders(sellList);
		List<OrderResult> OrderResultList = resultMap.get(currency.replaceAll("-", "_").toLowerCase());
		
		OkexGridPlan plan = new OkexGridPlan();
		OkexGridPlanExample planExa = new OkexGridPlanExample();
		for(OrderResult result:OrderResultList) {
			plan.setSellorderid(result.getOrder_id().toString());
			planExa.createCriteria().andSellidEqualTo(result.getClient_oid());
			planMapper.updateByExampleSelective(plan, planExa);
		}
	}
	
	private static List<PlaceOrderParam> exeSell(OkexGridPlanMapper planMapper,OkexGridPlanExample sellExample) {
		List<PlaceOrderParam> orderList = new ArrayList<PlaceOrderParam>();
		PlaceOrderParam orderParam = null;
		try {
			List<OkexGridPlan> gridPlanList = planMapper.selectByExample(sellExample);
			if(gridPlanList.size() < 1) {
				log.info("无需要卖出订单");
				return null;
			}
			log.info("查询可卖出订单数量为:"+gridPlanList.size());
			//封装委托卖出订单
			for(OkexGridPlan grid:gridPlanList) {
				orderParam = new PlaceOrderParam();
				orderParam.setClient_oid(grid.getSellid());
				orderParam.setType("limit");
				orderParam.setPrice(grid.getSellprice().toString());
				orderParam.setSize(grid.getAmount().toString());
				orderParam.setSide("sell");
				orderParam.setInstrument_id(grid.getCurrency());
				orderList.add(orderParam);
			}
		}catch(Exception e) {
			log.error("生成卖出订单失败",e);
		}
		
		return orderList;
	}
}
