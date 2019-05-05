package com.okex.trande.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.okex.trande.enums.OkexEnum;
import com.okex.trande.enums.OkexTradeEnums;
import com.okex.trande.serviceI.OkexPrivateServiceI;
import com.okex.trande.utils.HttpClientUtil;
import com.okex.trande.utils.SignatureUtils;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service("okexPrivateService")
public class OkexPrivateServiceImpl implements OkexPrivateServiceI{
	private static final String APIKEY = OkexEnum.OKEXQUERY.getApi_key();
	private static final String SECRETKEY = OkexEnum.OKEXQUERY.getSecret_key();
	private static final String DESC = OkexEnum.OKEXQUERY.getDesc();
	@Override
	public String getBalance() {
		Map<String,String> paramMap = new HashMap<String,String>();
		String response = getResponseMsg(paramMap,OkexTradeEnums.USERINFOURL.getUrl(),OkexTradeEnums.USERINFOURL.getDesc());
		return response;
	}
	@Override
	public String exeOrder(Map<String,String> paramMap) {
		//Map<String,String> paramMap = new HashMap<String,String>();
		String response = getResponseMsg(paramMap,OkexTradeEnums.TRADEURL.getUrl(),OkexTradeEnums.TRADEURL.getDesc());
		return response;
	}
	@Override
	public String exeBatchOrders() {
		Map<String,String> paramMap = new HashMap<String,String>();
		String response = getResponseMsg(paramMap,OkexTradeEnums.TRADEBATCHURL.getUrl(),OkexTradeEnums.TRADEBATCHURL.getDesc());
		return response;
	}
	@Override
	public String cancelOrder(Map<String,Object> paramMap) {
		List<String> orderList = (List<String>) paramMap.get("orderIds");
		StringBuffer orderStr = new StringBuffer("");
		String response = null;
		try {
			Map<String,String> map = new HashMap<String,String>();
			map.put("symbol",paramMap.get("symbol").toString());		
			for(int i = 0;i < orderList.size();i++) {
				orderStr.append(orderList.get(i));
				orderStr.append(",");
				if(i%2 ==0) {
					map.put("order_id", orderStr.replace(orderStr.length()-1, orderStr.length(), "").toString());
					response = getResponseMsg(map,OkexTradeEnums.CANCELORDERURL.getUrl(),OkexTradeEnums.CANCELORDERURL.getDesc());
					map.remove("order_id");
				}
			}
		}catch(Exception e) {
			log.error("取消订单执行异常",e);
		}
		
		
		return response;
	}
	@Override
	public String getOrderInfo() {
		Map<String,String> paramMap = new HashMap<String,String>();
		String response = getResponseMsg(paramMap,OkexTradeEnums.ORDERINFOURL.getUrl(),OkexTradeEnums.ORDERINFOURL.getDesc());
		return response;
	}
	@Override
	public String getOrdersInfo() {
		Map<String,String> paramMap = new HashMap<String,String>();
		String response = getResponseMsg(paramMap,OkexTradeEnums.ORDERSINFOURL.getUrl(),OkexTradeEnums.ORDERSINFOURL.getDesc());
		return response;
	}
	@Override
	public Map<String,List<String>> getHisOrders(Map<String,String> paramMap) {
		//Map<String,String> paramMap = new HashMap<String,String>();
		String response = getResponseMsg(paramMap,OkexTradeEnums.HISORDERSURL.getUrl(),OkexTradeEnums.HISORDERSURL.getDesc());
		JSONObject respObj = JSONObject.parseObject(response);
		JSONArray ordersArray = respObj.getJSONArray("orders");
		JSONObject orderObj = null;
		List<String> orderList = new ArrayList<String>();
		StringBuffer orderStr = new StringBuffer("");
		Map<String,List<String>> orderMap = new HashMap<String,List<String>>();
		if(respObj.getBooleanValue("result")) {
			if(respObj.getIntValue("total") > 0) {
				for(int i = 0; i<ordersArray.size();i++) {
					orderObj = JSONObject.parseObject(ordersArray.get(i).toString());
					orderList.add(orderObj.getString("orders_id"));
				}
			}
			orderMap.put("orderIds", orderList);
			
		}
		
		return orderMap;
	}
	private static String getResponseMsg(Map<String,String> paramMap,String url,String desc) {
		//Map<String,String> sendMsgMap = new HashMap<String,String>();		
		String response = null;
		try {
			paramMap.put("api_key", APIKEY);
			paramMap.put("sign", SignatureUtils.encrypt(paramMap, SECRETKEY));
			
			response = HttpClientUtil.postForm(url, paramMap, null, HttpClientUtil.CONNTIMEOUT, HttpClientUtil.READTIMEOUT);
			
			
		} catch (Exception e) {
			log.error(desc+"失败",e);
			return null;
		}
		log.info(desc+"结果:"+response);
		return response;
	}
}
