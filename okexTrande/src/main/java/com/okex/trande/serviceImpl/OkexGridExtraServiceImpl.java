package com.okex.trande.serviceImpl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.okcoin.commons.okex.open.api.bean.spot.param.PlaceOrderParam;
import com.okcoin.commons.okex.open.api.bean.spot.result.Account;
import com.okcoin.commons.okex.open.api.bean.spot.result.OrderResult;
import com.okcoin.commons.okex.open.api.bean.spot.result.Ticker;
import com.okcoin.commons.okex.open.api.service.spot.SpotAccountAPIService;
import com.okcoin.commons.okex.open.api.service.spot.SpotOrderAPIServive;
import com.okcoin.commons.okex.open.api.service.spot.SpotProductAPIService;
import com.okex.mybatis.dao.OkexExtMapper;
import com.okex.mybatis.dao.OkexGridPlanMapper;
import com.okex.mybatis.model.OkexGridConfig;
import com.okex.mybatis.model.OkexGridPlan;
import com.okex.mybatis.model.OkexGridPlanExample;
import com.okex.trande.serviceI.OkexGridExtraServiceI;
import com.okex.trande.utils.CommonUtils;
import com.okex.trande.utils.GridCalcUtils;

import lombok.extern.slf4j.Slf4j;
/**
 * 网格交易法额外补充
 * @author cy
 *
 */
@Slf4j
@Service("okexGridExtraService")
public class OkexGridExtraServiceImpl implements OkexGridExtraServiceI {
	@Autowired OkexGridPlanMapper gridPlanMapper;
	@Autowired SpotProductAPIService spotProductAPIService;
	@Autowired SpotOrderAPIServive spotOrderApiService;
	@Autowired SpotAccountAPIService spotAccountAPIService;
	@Autowired OkexExtMapper extMapper;
	private static int DOWN = BigDecimal.ROUND_DOWN;
	private static BigDecimal buyPrice,buyAmount;
	private static double x = 0.00;
	@Override
	public void execute(String currency) {
		BigDecimal buyOnePrice = null;
		PlaceOrderParam orderParam = null;
		OkexGridPlan plan = null;
		OkexGridPlanExample planExam = null;
		List<OkexGridPlan> planGridList = null;
		try {
			planExam = new OkexGridPlanExample();
			planExam.createCriteria().andBuystsEqualTo("filled").
			andSellstsEqualTo("filled").andCurrencyEqualTo(currency);	
			planGridList = gridPlanMapper.selectByExample(planExam);
			
			if(planGridList == null || planGridList.size() <0) {
				log.info("无成交交易,不执行补充计划");
				return;
			}
		}catch(Exception e) {
			log.error("查询交易失败",e);
			return;
		}
		try {
			init(currency);
		}catch(Exception e) {
			log.error("初始化参数失败",e);
			return;
		}
			
		try {	
			Ticker ticker = spotProductAPIService.getTickerByProductId(currency);		
			buyOnePrice = new BigDecimal(ticker.getBest_bid());
			for(OkexGridPlan gridPlan:planGridList) {
				plan = packGridPlan(spotAccountAPIService,gridPlan,buyOnePrice);			
				orderParam = packBuyParam(plan);
				log.info("执行计划订单:"+JSONObject.toJSONString(orderParam));
				OrderResult result = spotOrderApiService.addOrder(orderParam);
				if("-1".equals(result.getOrder_id().toString())) {
					log.info("交易失败: "+JSONObject.toJSONString(result));
					
					continue;
				}
				plan.setBuyid(result.getClient_oid());
				plan.setBuyorderid(result.getOrder_id().toString());
				plan.setBuysts("open");
				gridPlanMapper.insert(plan);
				planExam.clear();
				planExam.createCriteria().andBuyidEqualTo(gridPlan.getBuyid());
				gridPlan.setBuysts("9999");
				gridPlan.setSellsts("9999");
				gridPlanMapper.updateByExampleSelective(gridPlan, planExam);
			}
		}catch(Exception e) {
			log.error("额外补充网格交易执行异常",e);
		}
	}
	private void init(String currency) throws Exception {
		
		OkexGridConfig config = extMapper.selectOneGridConfig(currency);
		x = config.getX();
	
	}
	
	private static OkexGridPlan packGridPlan(SpotAccountAPIService spotAccountAPIService,OkexGridPlan gridPlan,BigDecimal buyOnePrice) {
		OkexGridPlan plan = new OkexGridPlan();
		//buyPrice = buyOnePrice.multiply(BigDecimal.ONE.subtract(BigDecimal.valueOf(x)));
		Account balanceStr = spotAccountAPIService.getAccountByCurrency("USDT");
		log.info("balance :"+ JSONObject.toJSONString(balanceStr));
		BigDecimal totalAmt = new BigDecimal(balanceStr.getAvailable());
		log.info("totalAmt = "+totalAmt +",actSellPrice = " + gridPlan.getActsellprice());
		if(totalAmt.compareTo(gridPlan.getActsellprice()) > 0) {
			totalAmt = gridPlan.getActsellprice();
		}
		buyAmount = totalAmt.divide(buyOnePrice,BigDecimal.ROUND_DOWN).setScale(4, DOWN);
		String currencyType  = gridPlan.getCurrency().replaceAll("-", "");
		plan.setBuyprice(buyOnePrice);
		plan.setAmount(buyAmount);
		plan.setBuyid(currencyType+CommonUtils.getTime()+"b");
		plan.setBuysts("00");
		plan.setCreateDate(new Date());
		plan.setSellprice(buyOnePrice.multiply(BigDecimal.ONE.add(BigDecimal.valueOf(x))));
		plan.setSellid(currencyType+CommonUtils.getTime()+"s");
		plan.setSellsts("00");
		plan.setCurrency(gridPlan.getCurrency());
		return plan;
	}
	
	private static PlaceOrderParam packBuyParam(OkexGridPlan plan) {
		PlaceOrderParam orderParam = new PlaceOrderParam();
		orderParam.setClient_oid(plan.getBuyid());
		orderParam.setType("limit");
		orderParam.setPrice(plan.getBuyprice().toString());
		orderParam.setSize(plan.getAmount().toString());
		orderParam.setSide("buy");			
		orderParam.setInstrument_id(plan.getCurrency());
		
		return orderParam;
	}

}
