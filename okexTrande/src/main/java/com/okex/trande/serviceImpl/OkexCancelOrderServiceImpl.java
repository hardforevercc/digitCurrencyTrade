package com.okex.trande.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.okcoin.commons.okex.open.api.bean.spot.param.OrderParamDto;
import com.okcoin.commons.okex.open.api.bean.spot.result.OrderInfo;
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
		
		List<OkexGridPlan> gridPlanList = gridPlanMapper.selectByExample(gridPlanExample);
		if(null == gridPlanList || gridPlanList.size() < 1 ) {
			log.info("无需要撤销顶订单");
			return;
		}
		List<OrderParamDto> orderParamList = new ArrayList<OrderParamDto>();
		OrderParamDto orderParam = null;
		List<Long> orderIdList = new ArrayList<Long>();
		for(OkexGridPlan grid:gridPlanList) {
			orderIdList.add(Long.valueOf(grid.getBuyorderid()));
		}
		orderParam = new OrderParamDto();
		orderParam.setInstrument_id(currency.toLowerCase());
		orderParam.setOrder_ids(orderIdList);
		orderParamList.add(orderParam);
		spotOrderApiService.cancleOrders(orderParamList);
		log.info("撤销订单执行完毕");
		List<OrderInfo> orderInfoList = spotOrderApiService.getOrders(currency, "cancel", null, null, null);
		log.info(JSONObject.toJSONString(orderInfoList));
	}

}
