package com.okex.trande.serviceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.okcoin.commons.okex.open.api.bean.spot.param.PlaceOrderParam;
import com.okcoin.commons.okex.open.api.bean.spot.result.Account;
import com.okcoin.commons.okex.open.api.bean.spot.result.OrderResult;
import com.okcoin.commons.okex.open.api.service.spot.SpotAccountAPIService;
import com.okcoin.commons.okex.open.api.service.spot.SpotOrderAPIServive;
import com.okcoin.commons.okex.open.api.service.spot.SpotProductAPIService;
import com.okex.mybatis.dao.OkexExtMapper;
import com.okex.mybatis.dao.OkexGridConfigMapper;
import com.okex.mybatis.dao.OkexGridPlanMapper;
import com.okex.mybatis.model.OkexGridConfig;
import com.okex.mybatis.model.OkexGridPlan;
import com.okex.mybatis.model.OkexGridPlanExample;
import com.okex.trande.serviceI.OkecGridServiceI;
import com.okex.trande.serviceI.OkexCancelOrderServiceI;
import com.okex.trande.serviceI.OkexPrivateServiceI;
import com.okex.trande.serviceI.OkexPublicServiceI;
import com.okex.trande.utils.CommonUtils;
import com.okex.trande.utils.GridCalcUtils;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service("okecGridService")
public class OkecGridServiceImpl implements OkecGridServiceI {
	@Autowired OkexGridConfigMapper gridConfMapper;
	@Autowired OkexGridPlanMapper gridPlanMapper;
	@Autowired OkexExtMapper extMapper;
	@Autowired OkexPublicServiceI okexPublicService;
	@Autowired OkexPrivateServiceI okexPrivateService;
	@Autowired SpotAccountAPIService spotAccountAPIService;
	@Autowired SpotProductAPIService spotProductAPIService;
	@Autowired SpotOrderAPIServive spotOrderApiService;
	@Autowired OkexCancelOrderServiceI cancelOrderService;
	
	private static BigDecimal buyPrice,sellPrice,amount,buyAmt,totalAmt,configAmt;
	private static double x = 0.00;
	private static int n = 0;
	private final static int ROUND_DOWN = BigDecimal.ROUND_HALF_DOWN;
	//private static String nowDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	@Override
	public void execute(String currency) {
		
		log.info(new Date()+currency+"网格交易执行开始");
		log.info(new Date()+currency+"撤销昨日订单");
		cancelOrderService.batchCancel(currency);
		init(currency);
		log.info(new Date()+currency+"交易策略分为"+n+"段执行");
		//获取买一价
		try {
			com.okcoin.commons.okex.open.api.bean.spot.result.Ticker ticker = spotProductAPIService.getTickerByProductId(currency);		
			buyPrice = new BigDecimal(ticker.getBest_bid());
		}catch(Exception e) {
			log.error("获取"+currency+"买一价失败",e);
			return;
		}		
		//获取可用余额
		try {
			Account balanceStr = spotAccountAPIService.getAccountByCurrency("USDT");
			totalAmt = new BigDecimal(balanceStr.getAvailable());
			if(totalAmt.compareTo(BigDecimal.ZERO) <= 0) {
				log.info("账户可交易金额为0,无法生成执行计划");
				return;
			}
			//若配置金额不为空且配置金额<余额 则使用配置金额作为总金额
			if(null != configAmt && configAmt.compareTo(BigDecimal.ZERO) >0) {
				if(configAmt.compareTo(totalAmt) < 0) {
					totalAmt = configAmt;
				}
			}
			totalAmt = totalAmt.setScale(4,ROUND_DOWN);
		}catch(Exception e) {
			log.error("获取账户可用余额失败",e);
			return;
		}
		log.info("查询金额为:buyPrice = "+buyPrice+",totalAmt = "+totalAmt);
		List<OkexGridPlan> gridPlanList = null;
		try {
			gridPlanList = createGridPlan(currency);
			extMapper.insertGridPlanList(gridPlanList);
		}catch(Exception e) {
			log.error("执行GridPlan数据入库异常",e);
			return;
		}	
		log.info(new Date()+currency+"网格交易计划表生成完成");
		log.info(new Date()+currency+"网格交易开始执行批量下单操作");
		List<PlaceOrderParam> orderList = null;
		try {
			orderList = createBatchOrders(gridPlanList,currency);
			int num = orderList.size()/10;
			int begNum = 0;
			int endNum = 0;
			if(orderList.size()%10 > 0) { num = orderList.size()/10 + 1;}
			for(int i = 0;i < num;i++) {
				begNum = i*10;
				endNum = (i+1)*10;
				if(orderList.size() < (i+1)*10) {endNum = orderList.size();}
				List<PlaceOrderParam> orderSubList = orderList.subList(begNum, endNum);				
				Map<String, List<OrderResult>> resultMap =  spotOrderApiService.addOrders(orderSubList);
				log.info("resultMap:"+JSONObject.toJSONString(resultMap));
				//返回币值对为: ada_usdt .replaceAll("-", "_")
				List<OrderResult> OrderResultList = resultMap.get(currency.toLowerCase());
				OkexGridPlan plan = null;
				OkexGridPlanExample planExa = null;
				for(OrderResult result:OrderResultList) {
					plan = new OkexGridPlan();
					planExa = new OkexGridPlanExample();
					plan.setBuyorderid(result.getOrder_id().toString());
					plan.setBuysts("open");//买入交易状态更新为交易中
					planExa.createCriteria().andBuyidEqualTo(result.getClient_oid());
					gridPlanMapper.updateByExampleSelective(plan, planExa);
				}
			}
			
		}catch(Exception e) {
			log.error("批量下单执行异常",e);
			return;
		}
		log.info(new Date()+currency+"批量下单操作执行完毕");
	}
	
	private static List<OkexGridPlan> createGridPlan(String currency){
		List<OkexGridPlan> gridPlan = new ArrayList<OkexGridPlan>();
		OkexGridPlan grid = null;
		String currencyType = currency.replaceAll("-", "");
		try {
			int tmp = n;
			Date currDate = new Date();
			for( int i = 0;i <= n && tmp >0;i++) {
				
				if(i > 0){
					buyPrice = buyPrice.multiply(BigDecimal.valueOf(1-x)).setScale(4, ROUND_DOWN);				
				}
				
				sellPrice = buyPrice.multiply(BigDecimal.valueOf(1+x)).setScale(4, ROUND_DOWN);
				//交易数量 = 总金额/n/买入价格
				log.info("totalamt = "+totalAmt+",buyPrice = "+buyPrice+", sellPrice = "+sellPrice);
				amount = totalAmt.divide(new BigDecimal(tmp),ROUND_DOWN).divide(buyPrice,ROUND_DOWN).setScale(4, ROUND_DOWN);
				buyAmt = amount.multiply(buyPrice).setScale(4,ROUND_DOWN);
				grid = new OkexGridPlan();
				grid.setBuyprice(buyPrice);
				grid.setSellprice(sellPrice);
				grid.setAmount(amount);
				grid.setBuyamt(buyAmt);
				grid.setCurrency(currency);
				grid.setBuyid(currencyType+CommonUtils.getTime()+i+"b");
				grid.setSellid(currencyType+CommonUtils.getTime()+i+"s");
				grid.setBuysts("00");
				grid.setSellsts("00");
				grid.setCreateDate(currDate);
				grid.setUpdateDate(currDate);
				gridPlan.add(grid);
				tmp -= 1;
				totalAmt = totalAmt.subtract(buyAmt);
			}
			
		}catch(Exception e) {
			log.info("处理"+currency+"生成下单计划表失败",e);
			return null;
		}
		return gridPlan;
	}
	
	private static List<PlaceOrderParam> createBatchOrders(List<OkexGridPlan> gridPlan,String currency) {
		List<PlaceOrderParam> orderList = new ArrayList<PlaceOrderParam>();
		PlaceOrderParam orderParam = null;
		//封装委托买入订单
		for(OkexGridPlan grid:gridPlan) {
			orderParam = new PlaceOrderParam();
			log.info(grid.getBuyid());
			orderParam.setClient_oid(grid.getBuyid());
			orderParam.setType("limit");
			orderParam.setPrice(grid.getBuyprice().toString());
			orderParam.setSize(grid.getAmount().toString());
			orderParam.setSide("buy");			
			orderParam.setInstrument_id(currency);
			orderParam.setMargin_trading((byte) 1);
			orderList.add(orderParam);
		}
		log.info("orderList:"+JSONObject.toJSONString(orderList));
		return orderList;
	}

	
	private void init(String currency) {
		try {
			OkexGridConfig config = extMapper.selectOneGridConfig(currency);
			n = GridCalcUtils.getN(config.getX(), config.getY());
			x = config.getX();
			configAmt = config.getTotalamt();
			log.info("x = "+config.getX()+",y = "+ config.getY());
			int filledNum = extMapper.selectLeftPlan(currency);
			n = n-filledNum;
		}catch(Exception e) {
			log.error("获取"+currency+"grid配置信息失败",e);
		}
		
	}
	
	
}
