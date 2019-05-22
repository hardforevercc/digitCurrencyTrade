package com.okex.trande.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AntDesignProController implements ErrorController{
	

	@Override
    public String getErrorPath(){
        return "/error";
    }

    @RequestMapping(value = "/error")
    public String getIndex(){
    	try {
    		log.info("成功访问index方法");    	
    		return "index"; //返回index页面
    	}catch(Exception e) {
    		log.error("解析页面失败:",e);
    		return "error";
    	}
        
    }

}
