package com.okex.trande.serviceImpl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.okcoin.commons.okex.open.api.bean.spot.param.PlaceOrderParam;
import com.okcoin.commons.okex.open.api.bean.spot.result.Account;
import com.okcoin.commons.okex.open.api.bean.spot.result.OrderResult;
import com.okcoin.commons.okex.open.api.bean.spot.result.Ticker;
import com.okcoin.commons.okex.open.api.service.spot.SpotAccountAPIService;
import com.okcoin.commons.okex.open.api.service.spot.SpotOrderAPIServive;
import com.okcoin.commons.okex.open.api.service.spot.SpotProductAPIService;
import com.okex.mybatis.dao.OkexDealChangePlanMapper;
import com.okex.mybatis.dao.OkexExtMapper;
import com.okex.mybatis.model.OkexDealChangePlan;
import com.okex.mybatis.model.OkexDealChangePlanExample;
import com.okex.mybatis.model.OkexGridConfig;
import com.okex.trande.serviceI.OkexDealChangeServiceI;
import com.okex.trande.utils.CommonUtils;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class OkexDealChangeServiceImpl implements OkexDealChangeServiceI {
	@Autowired OkexExtMapper extMapper;
	@Autowired SpotAccountAPIService spotAccountAPIService;
	@Autowired SpotProductAPIService spotProductAPIService;
	@Autowired SpotOrderAPIServive spotOrderApiService;
	@Autowired OkexDealChangePlanMapper planMapper;
	private static BigDecimal configAmt;
	@Override
	public void execute(String currency) {
		BigDecimal totalAmt = getAvaliable("USDT");
		if(null == totalAmt) {
			return;
		}
		log.info("获取{}可用余额成功:avaliable = {}","USDT",totalAmt);
		//获取当前可买入金额比例
		double percent = 0.00;
		BigDecimal buyPrice = null;
		try {
			Ticker ticker = spotProductAPIService.getTickerByProductId(currency);		
			buyPrice = new BigDecimal(ticker.getBest_bid());
			percent = extMapper.selectOnchangePercent(buyPrice,currency);
		}catch(Exception e) {
			log.error("获取支配资金比例失败",e);
			return;
		}
		log.info("获取{}可用余额成功:可用资金比例 = {}",currency,percent);
		/**
		 * 根据可利用资金生成买入订单：
		 * 1.当前价格买入,卖出成功则继续以当前价格买入
		 * 2.价格低于当前价格1%买入,卖出成功则继续以该策略执行
		 */
		percent = percent/2;
		totalAmt = totalAmt.multiply(BigDecimal.valueOf(percent));
		//生成执行计划
		OkexDealChangePlan plan1 = createOkexDealChangePlan(currency,totalAmt,buyPrice,"A");
		if(null == plan1) {
			log.info("plan1 执行计划生成失败");
			return;
		}
		BigDecimal buyPrice2 = buyPrice.multiply(BigDecimal.valueOf(1-0.01));
		OkexDealChangePlan plan2 = createOkexDealChangePlan(currency,totalAmt,buyPrice,"A");
		if(null == plan2) {
			log.info("plan2 执行计划生成失败");
			return;
		}
		createOrder(plan1);
		log.info("生成{}订单1:当前价买入成功,买入金额为 = {},买价格为 = {}",currency,totalAmt,buyPrice);
		createOrder(plan2);
		log.info("生成{}订单2:当前价买入成功,买入金额为 = {},买价格为 = {}",currency,totalAmt,buyPrice2);
		
		
	}
	
	private OkexDealChangePlan createOkexDealChangePlan(String currency,BigDecimal buyPrice,BigDecimal totalAmt,String flag) {
		OkexDealChangePlan plan = new OkexDealChangePlan();
		BigDecimal amount = CommonUtils.divide(totalAmt, buyPrice);
		BigDecimal sellPrice = buyPrice.multiply(BigDecimal.valueOf(1+0.01));
		String currencyType = currency.replaceAll("-", "");
		plan.setType("onChange");
		plan.setCurrency(currency);
		plan.setBuyprice(buyPrice);
		plan.setSellprice(sellPrice);
		plan.setAmount(amount);
		plan.setBuyamt(totalAmt);
		plan.setCurrency(currency);
		plan.setBuyid(currencyType+CommonUtils.getTime()+flag+"b");
		plan.setSellid(currencyType+CommonUtils.getTime()+flag+"s");
		plan.setBuysts("00");
		plan.setSellsts("00");
		plan.setCreateDate(new Date());
		plan.setUpdateDate(new Date());
		
		try {
			planMapper.insert(plan);
		}catch(Exception e) {
			log.error("生成{}执行计划失败",flag,e);
			return null;
		}
		
		return plan;
	}
	
	private boolean createOrder(OkexDealChangePlan plan) {
		boolean flag = false;
		String currencyType = plan.getCurrency().replaceAll("-", "");
		PlaceOrderParam order = new PlaceOrderParam();
		BigDecimal amount = plan.getBuyamt().divide(plan.getBuyprice(),BigDecimal.ROUND_HALF_DOWN);
		order.setClient_oid(currencyType+CommonUtils.getTime()+"b");
		order.setInstrument_id(plan.getCurrency());
		order.setPrice(plan.getBuyprice().toString());
		order.setSide("buy");
		order.setSize(amount.toString());
		try {
			OrderResult result1 = spotOrderApiService.addOrder(order);
			if("-1".equals(result1.getOrder_id())) {
				log.info("下单失败:"+JSONObject.toJSONString(result1));
				return true;
			}
			plan.setBuyorderid(result1.getOrder_id().toString());
			plan.setBuysts("filled");
			plan.setCreateDate(new Date());
			OkexDealChangePlanExample example = new OkexDealChangePlanExample();
			example.createCriteria().andBuyidEqualTo(plan.getBuyid());
			planMapper.updateByExampleSelective(plan, example);
			log.info(plan.getBuyid()+"买入成功");
		}catch(Exception e) {
			log.error("下单执行失败:",e);
			flag = true;
		}
		
		return flag;
	}
	
	@Override
	public BigDecimal getAvaliable(String currency) {
		BigDecimal avaliable = null;
		try {
			Account balanceStr = spotAccountAPIService.getAccountByCurrency("USDT");
			avaliable = new BigDecimal(balanceStr.getAvailable());
			//查询可用余额
			if(avaliable.compareTo(BigDecimal.ZERO) <= 0) {
				log.info("账户可交易金额为0,无法生成执行计划");
				return null;
			}
			//查询配置金额
			if(null == configAmt) {
				OkexGridConfig config = extMapper.selectOneGridConfig(currency);
				configAmt = config.getTotalamt();
			}
			//若配置金额不为空且配置金额<余额 则使用配置金额作为总金额
			if(null != configAmt && configAmt.compareTo(BigDecimal.ZERO) >0) {
				if(configAmt.compareTo(avaliable) < 0) {
					avaliable = configAmt;
				}
			}
			avaliable = avaliable.setScale(4,BigDecimal.ROUND_DOWN);
		}catch(Exception e) {
			log.error("获取"+currency+"可用余额失败",e);
		}
		
		return avaliable;
	}
	
	@Override
	public void loopDeal(String curreny) {
		//更新买卖完全成功订单
		OkexDealChangePlanExample example = new OkexDealChangePlanExample();
		example.createCriteria().andBuystsEqualTo("filled");
		List<OkexDealChangePlan> planList = planMapper.selectByExample(example);
		for(OkexDealChangePlan plan : planList) {
			spotOrderApiService.
			getOrderByOrderId(curreny, plan.getBuyorderid());
		}
		
		//查询订单是否交易成功
	}
	

}
