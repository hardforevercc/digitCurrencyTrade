package com.okex.trande.serviceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.okcoin.commons.okex.open.api.bean.spot.result.OrderInfo;
import com.okcoin.commons.okex.open.api.service.spot.SpotOrderAPIServive;
import com.okex.mybatis.dao.OkexExtMapper;
import com.okex.mybatis.dao.OkexGridPlanMapper;
import com.okex.mybatis.model.OkexGridPlan;
import com.okex.mybatis.model.OkexGridPlanExample;
import com.okex.trande.serviceI.OkecGridServiceI;
import com.okex.trande.serviceI.OkexGridByDayServiceI;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service("okexGridByDayService")
public class OkexGridByDayServiceImpl implements OkexGridByDayServiceI {
	@Autowired OkecGridServiceI gridSercice;
	@Autowired SpotOrderAPIServive spotOrderApiService;
	@Autowired OkexGridPlanMapper gridPlanMapper;
	@Autowired OkexExtMapper extMapper;
	private List<String> list;
	
	public OkexGridByDayServiceImpl() {
		if(null == list) {
			list = new ArrayList<String>();
			list.add("filled");
			list.add("9999");
		}
	}
	@Override
	public void execute(String currency) {
		
		try {
			
			//执行下单操作
			//gridSercice.execute(currency);
			//更新未成交订单
			queryDealSts(currency,"filled");
		}catch(Exception e) {
			log.error(currency+"GridByDay执行异常",e);
		}
		
	}

	@Override
	public void queryDealSts(String currency,String sts) {
		try {
			List<OrderInfo> infoList =  spotOrderApiService.
					getOrders(currency, sts, null, null, null);
			//log.info(JSONObject.toJSONString(infoList));
			if(null == infoList || infoList.size() < 1) {
				log.info("无状态为:"+sts+"交易记录");
				return;
			}
			log.info("状态为"+sts+"订单数量为:"+infoList.size());
			//log.info("状态为"+sts+"订单信息为:"+JSONObject.toJSONString(infoList));
			OkexGridPlan plan = null;
			OkexGridPlanExample planExam = new OkexGridPlanExample();
			
			int filledNum = 0;
			for(OrderInfo orderInfo : infoList) {
				plan = new OkexGridPlan();
				filledNum = extMapper.selectBuyOrderNum(orderInfo.getClient_oid());
				if(filledNum > 0) {
					log.info("需要更新记录为:"+JSONObject.toJSONString(orderInfo));
					if("buy".equals(orderInfo.getSide())) {
						plan.setBuyorderid(orderInfo.getOrder_id().toString());
						plan.setActbuyamount(new BigDecimal(orderInfo.getFilled_size()));
						plan.setActbuyprice(new BigDecimal(orderInfo.getFilled_notional()));
						plan.setBuysts(orderInfo.getStatus());
						plan.setUpdateDate(new Date());
						log.info("更新买入订单为:"+orderInfo.getOrder_id().toString());
						planExam.createCriteria().andBuyidEqualTo(orderInfo.getClient_oid()).andBuystsNotIn(list);
						gridPlanMapper.updateByExampleSelective(plan, planExam);
					}
				}
				filledNum = extMapper.selectSellOrderNum(orderInfo.getClient_oid());
				if(filledNum > 0) {
					log.info("需要更新卖出记录为:"+JSONObject.toJSONString(orderInfo));
					if("sell".equals(orderInfo.getSide())) {
						plan.setSellorderid(orderInfo.getOrder_id().toString());
						plan.setActsellamount(new BigDecimal(orderInfo.getFilled_size()));
						plan.setActsellprice(new BigDecimal(orderInfo.getFilled_notional()));
						plan.setSellsts(orderInfo.getStatus());
						plan.setUpdateDate(new Date());
						log.info("更新卖出订单为:"+orderInfo.getOrder_id().toString());
						planExam.createCriteria().andSellidEqualTo(orderInfo.getClient_oid().toString()).andSellstsNotIn(list);
						gridPlanMapper.updateByExampleSelective(plan, planExam);
					}
				}
					
							
			}
			log.info("更新订单状态完毕,执行卖出操作开始");
			
		}catch(Exception e) {
			log.error("交易执行异常",e);
		}
		
	}

}
