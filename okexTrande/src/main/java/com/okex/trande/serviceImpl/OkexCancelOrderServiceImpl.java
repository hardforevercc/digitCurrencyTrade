package com.okex.trande.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.okcoin.commons.okex.open.api.bean.spot.param.OrderParamDto;
import com.okcoin.commons.okex.open.api.bean.spot.param.PlaceOrderParam;
import com.okcoin.commons.okex.open.api.bean.spot.result.OrderInfo;
import com.okcoin.commons.okex.open.api.bean.spot.result.OrderResult;
import com.okcoin.commons.okex.open.api.service.spot.SpotOrderAPIServive;
import com.okex.mybatis.dao.OkexGridPlanMapper;
import com.okex.mybatis.model.OkexGridPlan;
import com.okex.mybatis.model.OkexGridPlanExample;
import com.okex.trande.serviceI.OkexCancelOrderServiceI;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("okexCancelOrderService")
public class OkexCancelOrderServiceImpl implements OkexCancelOrderServiceI {
	@Autowired OkexGridPlanMapper gridPlanMapper;
	@Autowired SpotOrderAPIServive spotOrderApiService;
	
	@Override
	public void batchCancel(String currency) {
		OkexGridPlanExample gridPlanExample = new OkexGridPlanExample();
		gridPlanExample.createCriteria().andCurrencyEqualTo(currency)
		.andBuystsEqualTo("open").andSellstsEqualTo("00");
		List<OkexGridPlan> gridPlanList = null;
		try {
			gridPlanList = gridPlanMapper.selectByExample(gridPlanExample);
			if(null == gridPlanList || gridPlanList.size() < 1 ) {
				log.info("无需要撤销顶订单");
				return;
			}
		}catch(Exception e) {
			log.error("查询订买入状态为open,卖出订单状态为00订单异常",e);
			return;
		}
		
		
		List<Long> orderIdList = new ArrayList<Long>();
		try {
			for(OkexGridPlan grid:gridPlanList) {
				orderIdList.add(Long.valueOf(grid.getBuyorderid()));
			}
			pageCancer(orderIdList,currency);
		}catch(Exception e) {
			log.error("撤销买入状态为open,卖出状态为00订单异常",e);
		}
		
		log.info("撤销订单执行完毕");
		List<OrderInfo> orderInfoList = null;
		try {
			orderInfoList = spotOrderApiService.getOrders(currency, "cancel", null, null, null);
			OkexGridPlan plan = null;
			OkexGridPlanExample planExa = null;
			for(OrderInfo orderInfo:orderInfoList) {
				orderInfo.getOrder_id();
				orderInfo.getStatus();
				plan = new OkexGridPlan();
				planExa = new OkexGridPlanExample();
				plan.setBuyorderid(orderInfo.getOrder_id().toString());
				plan.setBuysts("open");//买入交易状态更新为交易中
				planExa.createCriteria().andBuyorderidEqualTo(orderInfo.getOrder_id().toString());
				gridPlanMapper.updateByExampleSelective(plan, planExa);
			
			}
		}catch(Exception e) {
			log.error("更新撤销买入状态为open,卖出状态为00订单异常",e);
		}
		
		log.info(JSONObject.toJSONString(orderInfoList));
	}
	
	public void pageCancer(List<Long> orderIdList,String currency) {
		OrderParamDto orderParam = null;
		List<OrderParamDto> orderParamList = null;
		int num = orderIdList.size()/10;
		int begNum = 0;
		int endNum = 0;
		if(orderIdList.size()%10 > 0) { num = orderIdList.size()/10 + 1;}
		for(int i = 0;i < num;i++) {
			begNum = i*10;
			endNum = (i+1)*10;
			if(orderIdList.size() < (i+1)*10) {endNum = orderIdList.size();}
			List<Long> orderSubList = orderIdList.subList(begNum, endNum);				
			orderParamList = new ArrayList<OrderParamDto>();
			orderParam = new OrderParamDto();
			orderParam.setInstrument_id(currency.toLowerCase());
			orderParam.setOrder_ids(orderSubList);
			orderParamList.add(orderParam);
			spotOrderApiService.cancleOrders(orderParamList);
		}
	}
}
