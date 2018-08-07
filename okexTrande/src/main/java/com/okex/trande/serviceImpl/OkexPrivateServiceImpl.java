package com.okex.trande.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.okex.trande.enums.OkexEnum;
import com.okex.trande.serviceI.OkexPrivateServiceI;
import com.okex.trande.utils.HttpClientUtil;
import com.okex.trande.utils.SignatureUtils;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service("okexPrivateService")
public class OkexPrivateServiceImpl implements OkexPrivateServiceI{
	private static final String USERINFOURL = "https://www.okb.com/api/v1/userinfo.do";
	private static final String APIKEY = OkexEnum.OKEXQUERY.getApi_key();
	private static final String SECRETKEY = OkexEnum.OKEXQUERY.getSecret_key();
	private static final String DESC = OkexEnum.OKEXQUERY.getDesc();
	@Override
	public String getBalance() {
		Map<String,String> paramMap = new HashMap<String,String>();		
		String response = null;
		try {
			paramMap.put("api_key", APIKEY);
			paramMap.put("sign", SignatureUtils.encrypt(paramMap, SECRETKEY));
			response = HttpClientUtil.postForm(USERINFOURL, paramMap, null, HttpClientUtil.CONNTIMEOUT, HttpClientUtil.READTIMEOUT);
		} catch (Exception e) {
			log.error("查询用户信息失败",e);
		}
		log.info("用户信息查询结果:"+response);
		return response;
	}

}
