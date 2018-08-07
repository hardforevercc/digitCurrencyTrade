package com.okex.trande.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.okex.trande.serviceI.OkexPrivateServiceI;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequestMapping("/private")
public class OkexPrivateController {
	@Autowired
	OkexPrivateServiceI okexPrivateService;
	@RequestMapping("/getUserInfo")
	public String getBalance(HttpServletRequest request) {
		
		return okexPrivateService.getBalance();
	}
}
