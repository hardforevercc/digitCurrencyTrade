package com.okex.trande.serviceImpl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.okcoin.commons.okex.open.api.bean.spot.result.OrderInfo;
import com.okcoin.commons.okex.open.api.service.spot.SpotOrderAPIServive;
import com.okex.mybatis.dao.OkexGridPlanMapper;
import com.okex.mybatis.model.OkexGridPlan;
import com.okex.mybatis.model.OkexGridPlanExample;
import com.okex.trande.serviceI.OkexGridLoopStatusServiceI;
import com.okex.trande.serviceI.OkexGridSellServiceI;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("okexGridLoopStatusService")
public class OkexGridLoopStatusServiceImpl implements OkexGridLoopStatusServiceI {
	@Autowired SpotOrderAPIServive spotOrderApiService;
	@Autowired OkexGridPlanMapper gridPlanMapper;
	@Autowired OkexGridSellServiceI okexGridSellService;
	
	@Override
	public void execute(String currency) {
		while(true) {
			try {
				List<OrderInfo> infoList =  spotOrderApiService.
						getOrders(currency, "filled", null, null, null);
				log.info(JSONObject.toJSONString(infoList));
				if(null == infoList || infoList.size() < 1) {
					log.info("无已成单交易");
					return;
				}
				log.info("已成交订单数量为:"+infoList.size());
				OkexGridPlan plan = null;
				OkexGridPlanExample planExam = new OkexGridPlanExample();
				for(OrderInfo orderInfo : infoList) {
					orderInfo.getStatus();
					plan = new OkexGridPlan();
					
					if("buy".equals(orderInfo.getSide())) {
						plan.setActbuyamount(new BigDecimal(orderInfo.getFilled_size()));
						plan.setActbuyprice(new BigDecimal(orderInfo.getFilled_notional()));
						plan.setBuysts(orderInfo.getStatus());
						planExam.createCriteria().andBuyorderidEqualTo(orderInfo.getOrder_id().toString()).andBuystsNotEqualTo("filled");
					}
					
					if("sell".equals(orderInfo.getSide())) {
						plan.setActsellamount(new BigDecimal(orderInfo.getFilled_size()));
						plan.setActsellprice(new BigDecimal(orderInfo.getFilled_notional()));
						plan.setSellsts(orderInfo.getStatus());
						planExam.createCriteria().andSellorderidEqualTo(orderInfo.getOrder_id().toString()).andSellstsNotEqualTo("filled");
					}
					gridPlanMapper.updateByExampleSelective(plan, planExam);
				}
				log.info("更新订单状态完毕,执行卖出操作开始");
				okexGridSellService.execute(currency);
			}catch(Exception e) {
				log.error("交易执行异常");
			}
			
			log.info("执行卖出操作成功");
			try {
				Thread.sleep(300000);
			} catch (InterruptedException e) {
				log.info("睡眠线程执行异常",e);
				Thread.currentThread().notifyAll();
			}
			log.info("当前交易执行完毕");
		}
		
	}

}
