package com.okex.trande.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

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
	public String cancelOrder() {
		Map<String,String> paramMap = new HashMap<String,String>();
		String response = getResponseMsg(paramMap,OkexTradeEnums.CANCELORDERURL.getUrl(),OkexTradeEnums.CANCELORDERURL.getDesc());
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
	public String getHisOrders() {
		Map<String,String> paramMap = new HashMap<String,String>();
		String response = getResponseMsg(paramMap,OkexTradeEnums.HISORDERSURL.getUrl(),OkexTradeEnums.HISORDERSURL.getDesc());
		return response;
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
