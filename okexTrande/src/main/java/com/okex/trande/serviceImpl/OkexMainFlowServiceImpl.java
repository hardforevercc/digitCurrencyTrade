package com.okex.trande.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okex.trande.serviceI.OkexMainFlowServiceI;
import com.okex.trande.serviceI.OkexPrivateServiceI;
import com.okex.trande.serviceI.OkexPublicServiceI;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service("okexMainFlowService")
public class OkexMainFlowServiceImpl implements OkexMainFlowServiceI{
	@Autowired
	OkexPublicServiceI okexPublicService;
	@Autowired
	OkexPrivateServiceI okexPrivateService;
	
	public void Execute() {
		log.info("[***************OKEX TRADING START******************]");
		
	}
}
