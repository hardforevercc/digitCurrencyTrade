package com.okex.trande.serviceImpl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import com.okcoin.commons.okex.open.api.bean.spot.param.PlaceOrderParam;
import com.okcoin.commons.okex.open.api.bean.spot.result.Account;
import com.okcoin.commons.okex.open.api.bean.spot.result.Ticker;
import com.okcoin.commons.okex.open.api.service.spot.SpotAccountAPIService;
import com.okcoin.commons.okex.open.api.service.spot.SpotOrderAPIServive;
import com.okcoin.commons.okex.open.api.service.spot.SpotProductAPIService;
import com.okex.mybatis.dao.OkexExtMapper;
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
		String currencyType = currency.replaceAll("-", "");
		PlaceOrderParam order = new PlaceOrderParam();
		BigDecimal amount = totalAmt.divide(buyPrice,BigDecimal.ROUND_HALF_DOWN);
		order.setClient_oid(currencyType+CommonUtils.getTime()+"b");
		order.setInstrument_id(currency);
		order.setPrice(buyPrice.toString());
		order.setSide("buy");
		order.setSize(amount.toString());
		spotOrderApiService.addOrder(order);
		log.info("生成{}当前价买入成功,买入金额为 = {}",currency,amount);
		
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

}
