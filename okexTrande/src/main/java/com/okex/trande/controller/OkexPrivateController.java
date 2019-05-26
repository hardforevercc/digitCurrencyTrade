package com.okex.trande.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.okcoin.commons.okex.open.api.bean.spot.result.OrderInfo;
import com.okcoin.commons.okex.open.api.service.spot.SpotOrderAPIServive;
import com.okex.trande.serviceI.OkecGridServiceI;
import com.okex.trande.serviceI.OkexAdaMainFlowServiceI;
import com.okex.trande.serviceI.OkexAdaMainFlowV2ServiceI;
import com.okex.trande.serviceI.OkexGridByDayServiceI;
import com.okex.trande.serviceI.OkexGridLoopStatusServiceI;
import com.okex.trande.serviceI.OkexPrivateServiceI;
import com.okex.trande.utils.HttpUtils;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequestMapping("/private")
public class OkexPrivateController {
	@Autowired
	OkexPrivateServiceI okexPrivateService;
	@Autowired
	OkexAdaMainFlowServiceI okexAdaMainFlowService;
	@Autowired
	OkexAdaMainFlowV2ServiceI okexAdaMainFlowV2Service;
	@Autowired OkecGridServiceI okecGridService;
	@Autowired OkexGridLoopStatusServiceI loopService;
	@Autowired OkexGridByDayServiceI gridByDayService;
	@Autowired SpotOrderAPIServive spotOrderApiService;
	
	@RequestMapping("/getUserInfo")
	public String getBalance(HttpServletRequest request) {
		
		return okexPrivateService.getBalance();
	}
	@RequestMapping("/Adatrade")
	public String startAda(HttpServletRequest request) {
		try {
			okexAdaMainFlowService.execute();
		} catch (Exception e) {
			log.error("trade error",e);
		}
		return "ada trading start...";
	}
	
	@RequestMapping("/Adatrade2")
	public String getHistory(HttpServletRequest request) {
		Map<String,String> map = null;
		String reqMsg;
		try {
			//reqMsg = HttpUtils.getMsg(request);
			//map = JSONObject.parseObject(reqMsg,Map.class);
			okexAdaMainFlowV2Service.run();
		} catch (Exception e) {
			log.error("报文格式转换异常",e);
		}
		
		return "okex_trade_v2 starting";
	}
	@RequestMapping("/exeGrid")
	public String exeGrid(HttpServletRequest request) {
		String reqMsg = null;
		String currency = null;
		String resp = null;
		try {
			reqMsg = HttpUtils.getMsg(request);
			currency = JSONObject.parseObject(reqMsg).getString("currency");
			okecGridService.execute(currency);
		}catch(Exception e) {
			log.error("执行异常",e);
		}
		
		return resp;
	}
	
	@RequestMapping("/loopGrid")
	public String loopGridService(HttpServletRequest request) {
		String reqMsg = null;
		String currency = null;
		String resp = null;
		try {
			reqMsg = HttpUtils.getMsg(request);
			currency = JSONObject.parseObject(reqMsg).getString("currency");
			loopService.execute(currency);
		}catch(Exception e) {
			log.error("执行异常",e);
		}
		
		return resp;
	}
	
	@RequestMapping("/gridByDay")
	public String gridByDayService(HttpServletRequest request) {
		String reqMsg = null;
		String currency = null;
		String resp = null;
		try {
			reqMsg = HttpUtils.getMsg(request);
			currency = JSONObject.parseObject(reqMsg).getString("currency");
			gridByDayService.execute(currency);
		}catch(Exception e) {
			log.error("执行异常",e);
		}
		
		return resp;
	}
	
	@RequestMapping("/getList")
	public String getOrderList(HttpServletRequest request) {
		String reqMsg = null;
		String currency = null;
		String resp = null;
		try {
			reqMsg = HttpUtils.getMsg(request);
			currency = JSONObject.parseObject(reqMsg).getString("currency");
			//List<OrderInfo> orderInfoList = spotOrderApiService.getOrders(currency, null, null, null, null);
			OrderInfo order = spotOrderApiService.getOrderByOrderId(currency, "2889998928784384");
			resp = JSONObject.toJSONString(order);
		}catch(Exception e) {
			log.error("执行异常",e);
		}
		
		return resp;
	}
}
