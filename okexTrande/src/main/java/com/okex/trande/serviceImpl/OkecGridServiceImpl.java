package com.okex.trande.serviceImpl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okcoin.commons.okex.open.api.bean.spot.param.PlaceOrderParam;
import com.okcoin.commons.okex.open.api.bean.spot.result.Account;
import com.okcoin.commons.okex.open.api.service.spot.SpotAccountAPIService;
import com.okcoin.commons.okex.open.api.service.spot.SpotOrderAPIServive;
import com.okcoin.commons.okex.open.api.service.spot.SpotProductAPIService;
import com.okex.mybatis.dao.OkexExtMapper;
import com.okex.mybatis.dao.OkexGridConfigMapper;
import com.okex.mybatis.dao.OkexGridPlanMapper;
import com.okex.mybatis.model.OkexGridConfig;
import com.okex.mybatis.model.OkexGridPlan;
import com.okex.trande.serviceI.OkecGridServiceI;
import com.okex.trande.serviceI.OkexPrivateServiceI;
import com.okex.trande.serviceI.OkexPublicServiceI;
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
	
	private static BigDecimal buyPrice,sellPrice,amount,buyAmt,totalAmt;
	private static double x = 0.00;
	private static int n = 0;
	private final static int ROUND_DOWN = BigDecimal.ROUND_HALF_DOWN;
	private static String nowDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	@Override
	public void execute(String currency) {
		BigDecimal totalAmt;
		
		
		
		log.info(new Date()+currency+"网格交易执行开始");
		try {
			OkexGridConfig config = extMapper.selectOneGridConfig(currency);
			n = GridCalcUtils.getN(config.getX(), config.getY());
			x = config.getX();
			log.info("x = "+config.getX()+",y = "+ config.getY());
		}catch(Exception e) {
			log.error("获取"+currency+"grid配置信息失败",e);
		}
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
			totalAmt = new BigDecimal(balanceStr.getBalance()).setScale(4);
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
		}	
		log.info(new Date()+currency+"网格交易计划表生成完成");
		log.info(new Date()+currency+"网格交易开始执行批量下单操作");
		List<PlaceOrderParam> orderList = null;
		try {
			orderList = createBatchOrders(gridPlanList,currency);
			spotOrderApiService.addOrders(orderList);
			
		}catch(Exception e) {
			log.error("批量下单执行异常",e);
			return;
		}
		log.info(new Date()+currency+"批量下单操作执行完毕");
	}
	
	private static List<OkexGridPlan> createGridPlan(String currency){
		List<OkexGridPlan> gridPlan = new ArrayList<OkexGridPlan>();
		OkexGridPlan grid = null;
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
				grid.setAmount(amount.intValue());
				grid.setBuyamt(buyAmt);
				grid.setCurrency(currency);
				grid.setBuyid(nowDate+i+"b");
				grid.setSellid(nowDate+i+"s");
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
			orderParam.setClient_oid(grid.getBuyid());
			orderParam.setType("limit");
			orderParam.setPrice(grid.getBuyprice().toString());
			orderParam.setSide("buy");
			orderParam.setInstrument_id(currency);
			orderList.add(orderParam);
		}
		//封装委托卖出订单
		for(OkexGridPlan grid:gridPlan) {
			orderParam = new PlaceOrderParam();
			orderParam.setClient_oid(grid.getSellid());
			orderParam.setType("limit");
			orderParam.setPrice(grid.getSellprice().toString());
			orderParam.setSide("sell");
			orderParam.setInstrument_id(currency);
			orderList.add(orderParam);
		}
		
		return orderList;
	}

}
