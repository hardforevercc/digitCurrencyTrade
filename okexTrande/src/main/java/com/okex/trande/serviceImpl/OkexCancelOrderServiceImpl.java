package com.okex.trande.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.okcoin.commons.okex.open.api.bean.spot.param.OrderParamDto;
import com.okcoin.commons.okex.open.api.bean.spot.param.PlaceOrderParam;
import com.okcoin.commons.okex.open.api.bean.spot.result.BatchOrdersResult;
import com.okcoin.commons.okex.open.api.bean.spot.result.OrderInfo;
import com.okcoin.commons.okex.open.api.bean.spot.result.OrderResult;
import com.okcoin.commons.okex.open.api.service.spot.SpotOrderAPIServive;
import com.okex.mybatis.dao.OkexExtMapper;
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
	@Autowired OkexExtMapper extMapper;
	
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
			orderInfoList = spotOrderApiService.getOrders(currency, "cancelled", null, null, null);
			
			log.info(JSONObject.toJSONString(orderInfoList));
	
			for(OrderInfo orderInfo:orderInfoList) {
				if(!"-1".equals(orderInfo)) {
					extMapper.upateTo9999(orderInfo.getClient_oid(),currency);
				}else {
					log.info("撤单失败订单:"+JSONObject.toJSONString(orderInfo));
				}
				
			}
		}catch(Exception e) {
			log.error("更新撤销买入状态为open,卖出状态为00订单异常",e);
		}
		
		
	}
	
	public void pageCancer(List<Long> orderIdList,String currency) {
		OrderParamDto orderParam = null;
		List<OrderParamDto> orderParamList = null;
		int num = orderIdList.size()/10;
		int begNum = 0;
		int endNum = 0;
		if(orderIdList.size()%10 > 0) { num = orderIdList.size()/10 + 1;}
		Map<String, BatchOrdersResult> resultMap = null;
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
			log.info("批量撤单请求数据为:"+JSONObject.toJSONString(orderParamList));
			resultMap = spotOrderApiService.cancleOrders_post(orderParamList);
			log.info("执行批量撤单结果为:"+JSONObject.toJSONString(resultMap));
			//
		}
		
	}

	@Override
	public void cancel(String currency) {
		OkexGridPlanExample  exam = new OkexGridPlanExample();
		exam.createCriteria().andCurrencyEqualTo(currency).andBuystsEqualTo("open")
		.andSellstsEqualTo("00");
		List<OkexGridPlan> gridList =  gridPlanMapper.selectByExample(exam);
		for(OkexGridPlan grid : gridList) {
			log.info("{}需处理撤销订单为：{}",currency,JSONObject.toJSONString(grid));
			PlaceOrderParam orderParam  =  new PlaceOrderParam();
			orderParam.setInstrument_id(grid.getCurrency().toLowerCase());
			orderParam.setClient_oid(grid.getBuyid());
			orderParam.setOrder_id(grid.getBuyorderid());
			log.info("{}撤销订单请求数据为：{}",currency,JSONObject.toJSONString(orderParam));
			OrderResult result = spotOrderApiService.cancleOrderByOrderId(orderParam, grid.getSellorderid());
			log.info("撤销: "+grid.getBuyid()+"结果 :"+JSONObject.toJSONString(result));
			if(!"-1".equals(result.getOrder_id().toString())){
				extMapper.upateTo9999(grid.getBuyid(),currency);
			}
		}
	}
}
