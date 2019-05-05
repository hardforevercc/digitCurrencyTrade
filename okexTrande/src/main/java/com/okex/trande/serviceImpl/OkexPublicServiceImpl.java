package com.okex.trande.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.okex.bean.DepthBean;
import com.okex.bean.TickerBean;
import com.okex.bean.TickerDetail;
import com.okex.trande.serviceI.OkexPublicServiceI;
import com.okex.trande.test.Test;
import com.okex.trande.utils.HttpClientUtil;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service("okexPublicService")
public class OkexPublicServiceImpl implements OkexPublicServiceI{
	
	private static String marketDepthUrl = "https://www.okex.me/api/spot/v3/instruments/%s/book";
	private static String trandRecordUrl = "https://www.okex.me/api/spot/v3/instruments/%s/trades";
	private static String tickerUrl = "https://www.okex.me/api/spot/v3/instruments/%s/ticker";
	private static final String CHARSET = "utf-8";
	/**
	 * 获取交易ticker
	 */
	@Override
	public String getTicker(String currency) {
		
		if(StringUtils.isBlank(currency)) {
			currency = "bch-btc";
		}
		tickerUrl = String.format(tickerUrl, currency);
		StringBuffer buffer = new StringBuffer("");
		String tickerResp = null;
		JSONObject obj = null;
		try {
			tickerResp = HttpClientUtil.get(tickerUrl, CHARSET, HttpClientUtil.CONNTIMEOUT, HttpClientUtil.READTIMEOUT);
			log.info("ticker info:["+tickerResp+"]");			
		} catch (Exception e) {			
			log.error("查询OKEX TICKER处理异常",e);
		}
		return tickerResp;
	}
	/**
	 * 获取市场明细
	 */
	@Override
	public String getMarket(String currency) {
		if(StringUtils.isBlank(currency)) {
			currency = "";
		}
		marketDepthUrl = String.format(marketDepthUrl, currency);
		String marketResp = null;
		
		try {
			marketResp = HttpClientUtil.get(marketDepthUrl, CHARSET, HttpClientUtil.CONNTIMEOUT, HttpClientUtil.READTIMEOUT);
		} catch (Exception e) {
			log.error("调用OKEX查询市场深度异常",e);
			return null;
		}
		log.info("marketDepth info:["+marketResp+"]");
		
		return marketResp;
	}
	/**
	 * 获取交易深度
	 */
	@Override
	public String getDepth(String currency) {
		String depthResp = null;
		if(StringUtils.isBlank(currency)) {
			currency = "bch_btc";
		}
		trandRecordUrl = String.format(trandRecordUrl, currency);
		try {
			depthResp = HttpClientUtil.get(trandRecordUrl, CHARSET, HttpClientUtil.CONNTIMEOUT, HttpClientUtil.READTIMEOUT);
		} catch (Exception e) {
			log.error("查询OKEX市场深度异常",e);
		}
		log.info("trandRecord info:["+depthResp+"]");
		List<DepthBean> recordArr = JSONObject.parseArray(depthResp,DepthBean.class);
		log.info(""+recordArr.size());
		for(DepthBean record:recordArr) {
			if("sell".equals(record.getType())) {
				log.info(record.getType()+"|"+record.getAmount()+"|"+record.getPrice());
			}else {
				log.info(record.getType()+"|"+record.getAmount()+"|"+record.getPrice());
			}
		}
		return depthResp;
	}

}
