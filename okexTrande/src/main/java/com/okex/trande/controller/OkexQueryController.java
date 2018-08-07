package com.okex.trande.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.okex.trande.serviceI.OkexPublicServiceI;
import com.okex.trande.utils.HttpUtils;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequestMapping("/base")
public class OkexQueryController {
	@Autowired
	OkexPublicServiceI okexPublicService;
	@RequestMapping("/test")
	@ResponseBody
	public String test(HttpServletRequest request) {
		String currency = null;
		try {
			currency = HttpUtils.getMsg(request);
		} catch (IOException e) {
			log.error("内部系统处理错误",e);
		}
		return "===Hello trander===\r\n"+okexPublicService.getTicker(currency);
	}
	@RequestMapping("/index")
	@ResponseBody
	public String index() {
		return null;
		
	}
}
