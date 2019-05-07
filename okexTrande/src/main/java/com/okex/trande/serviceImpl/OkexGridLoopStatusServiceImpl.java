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
import com.okex.trande.serviceI.OkexGridByDayServiceI;
import com.okex.trande.serviceI.OkexGridLoopStatusServiceI;
import com.okex.trande.serviceI.OkexGridSellServiceI;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("okexGridLoopStatusService")
public class OkexGridLoopStatusServiceImpl implements OkexGridLoopStatusServiceI {
	@Autowired SpotOrderAPIServive spotOrderApiService;
	@Autowired OkexGridPlanMapper gridPlanMapper;
	@Autowired OkexGridSellServiceI okexGridSellService;
	@Autowired OkexGridByDayServiceI okexGridByDayService;
	@Override
	public void execute(String currency) {
		while(true) {
			try {
				okexGridByDayService.queryDealSts(currency, "filled");
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
