package com.okex.trande.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.okex.bean.DepthBean;
import com.okex.bean.TickerBean;
import com.okex.bean.TickerDetail;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class OkexTradeUtils {
	/**
	 * 处理ticker
	 * @param tickerResp
	 * @return
	 */
	public static TickerBean dealTicker(String tickerResp) {
		TickerDetail ticker = null;
		TickerBean tickerObj = null;
		if(StringUtils.isNotBlank(tickerResp)) {
			tickerObj = JSONObject.parseObject(tickerResp, TickerBean.class);			
		}
		return tickerObj;
	}
	/**
	 * 处理市场数据
	 * @param marketResp
	 * @return
	 */
	public static String dealMarket(String marketResp) {
		JSONObject obj = null;
		StringBuffer buffer = new StringBuffer("");
		if(StringUtils.isNotBlank(marketResp)) {
			obj =JSONObject.parseObject(marketResp);
			JSONArray askArray = obj.getJSONArray("asks");
			JSONArray bidArray = obj.getJSONArray("bids");
			log.info("asks大小为:"+askArray.size());
			log.info("bids大小为:"+bidArray.size());
			for(int i=0;i<askArray.size();i++) {
				JSONArray askArrDetail = (JSONArray) askArray.get(i);					
				log.info(i+"ask为:"+askArrDetail.get(0)+",1为："+askArrDetail.get(1));
				if(i>=bidArray.size()) {
					continue;
				}
				JSONArray bidArrDetail = (JSONArray) bidArray.get(i);
				log.info(i+"bid为:"+bidArrDetail.get(0)+",1为："+bidArrDetail.get(1));
			}			
		}
		return null;
	}
	/**
	 * 市场深度处理类
	 * @param depthResp
	 * @return
	 */
	public static String dealDepth(String depthResp) {
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
	public static Map<String,String> getFreeAc(String balance,String currency) throws Exception{
		
		if(StringUtils.isBlank(balance)) {
			throw new Exception("the userinfo is empty!");
		}
		JSONObject balanceObj = JSONObject.parseObject(balance);
		if(!"true".equals(balanceObj.getString("result"))) {
			throw new Exception("the userinfo failed!");
		}
		JSONObject curencyAc = balanceObj.getJSONObject("info").getJSONObject("funds").getJSONObject("free");
		String adaAc = curencyAc.getString("ada");
		String usdtAc = curencyAc.getString("usdt");
		log.info("adaAc:"+adaAc+",usdtAc:"+usdtAc);
		Map<String,String> acMap = new HashMap<String,String>();
		acMap.put("adaAc", adaAc);
		acMap.put("usdtAc", usdtAc);
		return acMap;
	}
}
