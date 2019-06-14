package com.okex.trande.serviceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
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
		log.info("执行卖出交易币种: "+currency);
		OkexGridPlanExample sellExample = new OkexGridPlanExample();
		sellExample.createCriteria().andBuystsEqualTo("filled").andSellstsEqualTo("00")
		.andCurrencyEqualTo(currency);
		List<PlaceOrderParam> sellList = exeSell(planMapper,sellExample);
		if(null == sellList) {
			return;
		}
		log.info("卖单执行结果为:"+JSONObject.toJSONString(sellList));
		Map<String, List<OrderResult>> resultMap =  spotOrderApiService.addOrders(sellList);
		//okex 返回报文变更.replaceAll("-", "_")
		List<OrderResult> OrderResultList = resultMap.get(currency.toLowerCase());
		log.info("卖单执行结果为:"+JSONObject.toJSONString(OrderResultList));
		OkexGridPlan plan = new OkexGridPlan();
		OkexGridPlanExample planExa = new OkexGridPlanExample();
		for(OrderResult result:OrderResultList) {
			if(-1 == result.getOrder_id()) {
				planExa.createCriteria().andSellidEqualTo(result.getClient_oid());
				List<OkexGridPlan> planList = planMapper.selectByExample(planExa);
				plan.setAmount(planList.get(0).getAmount().subtract(BigDecimal.ONE));
				planMapper.updateByExampleSelective(plan, planExa);
				log.info(currency+" 更新下单失败数据成功: "+result.getClient_oid());
			}else {
				plan.setSellorderid(result.getOrder_id().toString());
				plan.setSellsts("open");
				planExa.createCriteria().andSellidEqualTo(result.getClient_oid());
				planMapper.updateByExampleSelective(plan, planExa);
			}
			
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
				log.info("执行卖出订单:"+ grid.getSellid());
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
