package com.okex.trande.serviceImpl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.okcoin.commons.okex.open.api.bean.spot.param.PlaceOrderParam;
import com.okcoin.commons.okex.open.api.bean.spot.result.Account;
import com.okcoin.commons.okex.open.api.bean.spot.result.OrderInfo;
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
@Service("okexDealChangeService")
public class OkexDealChangeServiceImpl implements OkexDealChangeServiceI {
	@Autowired OkexExtMapper extMapper;
	@Autowired SpotAccountAPIService spotAccountAPIService;
	@Autowired SpotProductAPIService spotProductAPIService;
	@Autowired SpotOrderAPIServive spotOrderApiService;
	@Autowired OkexDealChangePlanMapper planMapper;
	private static BigDecimal configAmt;
	private static BigDecimal x;
	private int id = 0;
	@Override
	public void execute(String currency) {
		BigDecimal totalAmt = getAvaliable(currency);
		if(null == totalAmt) {
			return;
		}
		log.info("获取{}可用余额成功:avaliable = {}","USDT",totalAmt);
		//获取当前可买入金额比例
		BigDecimal percent = null;
		BigDecimal buyPrice = null;
		Map<String,BigDecimal> dealMap = null;
		Ticker ticker = null;
		try {
			ticker = spotProductAPIService.getTickerByProductId(currency);		
			buyPrice = new BigDecimal(ticker.getBest_bid());
			dealMap = extMapper.selectDealChangeConfig(currency,ticker.getBest_bid());
			percent = dealMap.get("deal_percent");
			x = dealMap.get("x");
		}catch(Exception e) {
			log.error("获取支配资金比例失败",e);
			return;
		}
		log.info("获取{}可用余额成功:可用资金比例 = {}",currency,percent);
		
		try {
			id = extMapper.selectPriceId(currency, buyPrice.toString());
			log.info("当前价格处于ID位置为 "+id);
			if(id == 0) {
				return;
			}
			updateOrder(currency);
			OkexDealChangePlanExample example = new OkexDealChangePlanExample();
			example.createCriteria().andBuystsEqualTo("filled").andSellstsEqualTo("filled")
			.andCurrencyEqualTo(currency);
			OkexDealChangePlan plan = new OkexDealChangePlan();
			plan.setBuysts("9999");
			plan.setSellsts("9999");
			planMapper.updateByExampleSelective(plan, example);
		}catch(Exception e) {
			log.error("状态为 filled filled 订单更新失败",e);
			return;
		}
		log.info("状态为 filled filled 订单更新成功");
		/**
		 * 根据可利用资金生成买入订单：
		 * 1.当前价格买入,卖出成功则继续以当前价格买入
		 * 2.价格低于当前价格1%买入,卖出成功则继续以该策略执行
		 */
		percent = CommonUtils.divide(percent, BigDecimal.valueOf(2));	
		totalAmt = totalAmt.multiply(percent);
		//查询是否存在未完成A 查询是否存在未完成B
		try {
			int aNum = extMapper.selectDealChangeRecord(currency,ticker.getBest_bid(),"A");
			//生成执行计划
			if(aNum < 1) {
				BigDecimal plan1BuyPrice = new BigDecimal(ticker.getBest_ask());
				OkexDealChangePlan plan1 = createOkexDealChangePlan(currency,totalAmt,plan1BuyPrice,"A");
				if(null == plan1) {
					log.info("plan1 执行计划生成失败");
					return;
				}
				createOrder(plan1);
				log.info("生成{}订单1:当前价买入成功,买入金额为 = {},买价格为 = {}",currency,totalAmt,plan1BuyPrice);
			}else {
				log.info("已存在A类订单无需生成新订单");
			}
		}catch(Exception e) {
			log.error("生成A类订单失败",e);
			return;
		}
		
		try {
			int bNum = extMapper.selectDealChangeRecord(currency,ticker.getBest_bid(),"B");
			if(bNum < 1) {
				BigDecimal buyPrice2 = new BigDecimal(ticker.getBest_bid()).multiply(BigDecimal.ONE.subtract(x));
				OkexDealChangePlan plan2 = createOkexDealChangePlan(currency,totalAmt,buyPrice2,"B");
				if(null == plan2) {
					log.info("plan2 执行计划生成失败");
					return;
				}
				createOrder(plan2);
				log.info("生成{}订单2:当前价买入成功,买入金额为 = {},买价格为 = {}",currency,totalAmt,buyPrice2);
			}else{
				log.info("已存在B类订单无需生成新订单");
			}	
		}catch(Exception e) {
			log.error("生成B类订单失败",e);
			return;
		}
		
	}
	
	private OkexDealChangePlan createOkexDealChangePlan(String currency,BigDecimal totalAmt,BigDecimal buyPrice,String flag) {
		OkexDealChangePlan plan = new OkexDealChangePlan();
		BigDecimal amount = CommonUtils.divide(totalAmt, buyPrice);
		BigDecimal sellPrice = buyPrice.multiply(BigDecimal.ONE.add(x));
		String currencyType = currency.replaceAll("-", "");
		plan.setType(flag);
		plan.setConfigId(id);
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
		order.setClient_oid(plan.getBuyid());
		order.setInstrument_id(plan.getCurrency());
		order.setPrice(plan.getBuyprice().toString());
		order.setSide("buy");
		order.setSize(amount.toString());
		try {
			OrderResult result1 = spotOrderApiService.addOrder(order);
			if("-1".equals(result1.getOrder_id().toString())) {
				log.info("下单失败:"+JSONObject.toJSONString(result1));
				return true;
			}
			plan.setBuyorderid(result1.getOrder_id().toString());
			plan.setBuysts("open");
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

	@Override
	public void updateOrder(String currency) {
		OkexDealChangePlanExample example = new OkexDealChangePlanExample();
		example.createCriteria().andBuystsEqualTo("open").
		andCurrencyEqualTo(currency);
		List<OkexDealChangePlan> dealBuyPlanList =  planMapper.selectByExample(example);
		if(null == dealBuyPlanList || dealBuyPlanList.size() < 1) {
			log.info("买入类挂单 交易状态为 open 数量为空");
			return;
		}else {
			updateOrderById(dealBuyPlanList, currency);
		}
		example.clear();
		example.createCriteria().andSellstsEqualTo("open").
		andCurrencyEqualTo(currency);
		List<OkexDealChangePlan> dealSellPlanList =  planMapper.selectByExample(example);
		if(null == dealSellPlanList || dealSellPlanList.size() < 1) {
			log.info("卖出类挂单 交易状态为 open 数量为空");
			return;
		}else {
			updateOrderById(dealSellPlanList, currency);
		}	
	}

	@Override
	public void updateOrderById(List<OkexDealChangePlan> dealAPlanList, String currency) {
		OkexDealChangePlanExample example = new OkexDealChangePlanExample();
		
		for(OkexDealChangePlan dealAPlan:dealAPlanList) {
			OrderInfo info = spotOrderApiService.getOrderByOrderId(currency, dealAPlan.getBuyorderid());
			log.info(JSONObject.toJSONString(info));
			if ("buy".equals(info.getSide())) {
				if ("filled".equals(info.getStatus())) {

					example.createCriteria().andBuystsNotIn(Arrays.asList("filled", "9999"))
							.andCurrencyEqualTo(currency).andBuyorderidEqualTo(info.getOrder_id().toString());
					dealAPlan.setActbuyamount(new BigDecimal(info.getFilled_size()));
					dealAPlan.setActbuyamt(new BigDecimal(info.getFilled_notional()));
					dealAPlan.setUpdateDate(new Date());
					dealAPlan.setBuysts(info.getStatus());
					dealAPlan.setId(null);
					planMapper.updateByExampleSelective(dealAPlan, example);
					log.info("更新{}订单 ：{}成功", info.getSide(), info.getOrder_id());
					if ("filled".equals(dealAPlan.getBuysts()) && "00".equals(dealAPlan.getSellsts())) {
						PlaceOrderParam order = new PlaceOrderParam();
						order.setClient_oid(dealAPlan.getSellid());
						order.setInstrument_id(dealAPlan.getCurrency());
						order.setPrice(dealAPlan.getSellprice().toString());
						order.setSide("sell");
						order.setSize(dealAPlan.getActbuyamount().multiply(BigDecimal.ONE.subtract(x)).toString());
						try {
							OrderResult result1 = spotOrderApiService.addOrder(order);
							if ("-1".equals(result1.getOrder_id().toString())) {
								log.warn("下单失败:" + JSONObject.toJSONString(result1));
								return;
							} else {
								example.clear();
								example.createCriteria().andSellidEqualTo(dealAPlan.getSellid());
								dealAPlan.setSellsts("open");
								dealAPlan.setSellorderid(result1.getOrder_id().toString());
								dealAPlan.setUpdateDate(new Date());
								planMapper.updateByExampleSelective(dealAPlan, example);
							}
						} catch (Exception e) {
							log.error("卖单执行失败", e);
						}
					}
				}

			}
			
			if ("sell".equals(info.getSide()) && "filled".equals(info.getStatus())) {
				example.clear();
				example.createCriteria().andSellidEqualTo(dealAPlan.getSellid());
				dealAPlan.setSellsts(info.getSide());
				dealAPlan.setActsellamount(new BigDecimal(info.getFilled_size()));
				dealAPlan.setActbuyamt(new BigDecimal(info.getFilled_notional()));
				dealAPlan.setUpdateDate(new Date());
				planMapper.updateByExampleSelective(dealAPlan, example);
			}
		}
	}
	

}
