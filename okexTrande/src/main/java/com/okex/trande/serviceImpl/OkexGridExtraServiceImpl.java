package com.okex.trande.serviceImpl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okcoin.commons.okex.open.api.bean.spot.param.PlaceOrderParam;
import com.okcoin.commons.okex.open.api.service.spot.SpotOrderAPIServive;
import com.okcoin.commons.okex.open.api.service.spot.SpotProductAPIService;
import com.okex.mybatis.dao.OkexGridPlanMapper;
import com.okex.mybatis.model.OkexGridPlan;
import com.okex.mybatis.model.OkexGridPlanExample;
import com.okex.trande.serviceI.OkexGridExtraServiceI;

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
	
	private static int DOWN = BigDecimal.ROUND_DOWN;
	private static BigDecimal buyPrice,buyAmount;
	@Override
	public void execute() {
		OkexGridPlanExample planExam = new OkexGridPlanExample();
		planExam.createCriteria().andBuystsEqualTo("filled").andSellstsEqualTo("filled");
		List<OkexGridPlan> planGridList = gridPlanMapper.selectByExample(planExam);
		PlaceOrderParam orderParam = null;
		OkexGridPlan plan = new OkexGridPlan();
		for(OkexGridPlan gridPlan:planGridList) {
			//获取买一价
			buyPrice = gridPlan.getBuyprice().multiply(BigDecimal.ONE.add(BigDecimal.valueOf(0.01)));
			buyAmount = gridPlan.getActsellprice().divide(buyPrice).setScale(4, DOWN);
			
			plan.setBuyprice(buyPrice);
			plan.setAmount(buyAmount);
			plan.setBuyid(buyid);
			plan.setBuysts("00");
			plan.setCreateDate(new Date());
			plan.setSellprice(gridPlan.getSellprice().multiply(BigDecimal.ONE.add(BigDecimal.valueOf(0.01))));
			plan.setSellid(sellid);
			plan.setSellsts("00");
			
			orderParam = new PlaceOrderParam();
			orderParam.setClient_oid(grid.getBuyid());
			orderParam.setType("limit");
			orderParam.setPrice(grid.getBuyprice().toString());
			orderParam.setSize(grid.getAmount().toString());
			orderParam.setSide("buy");			
			orderParam.setInstrument_id(currency);
			orderList.add(orderParam);
			spotOrderApiService.addOrder(orderParam);
		}

	}

}
