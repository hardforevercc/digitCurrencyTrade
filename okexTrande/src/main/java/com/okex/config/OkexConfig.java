package com.okex.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.okcoin.commons.okex.open.api.config.APIConfiguration;
import com.okcoin.commons.okex.open.api.service.spot.SpotAccountAPIService;
import com.okcoin.commons.okex.open.api.service.spot.impl.SpotAccountAPIServiceImpl;
import com.okcoin.commons.okex.open.api.service.spot.impl.SpotOrderApiServiceImpl;
import com.okcoin.commons.okex.open.api.service.spot.impl.SpotProductAPIServiceImpl;
import com.okex.trande.enums.OkexEnum;
@Configuration
public class OkexConfig {
	
	@Bean
    public APIConfiguration okexApiConfig() {
        APIConfiguration config = new APIConfiguration();
        config.setEndpoint("https://www.okex.me");
        config.setApiKey(OkexEnum.OKEXTRADE.getApi_key());
        config.setSecretKey(OkexEnum.OKEXTRADE.getSecret_key());
        config.setPassphrase("cc1234");
        config.setPrint(false);
        return config;
    }
	
	@Bean
    public SpotAccountAPIService spotAccountAPIService(APIConfiguration config) {
        return new SpotAccountAPIServiceImpl(config);
    }
	
	@Bean
    public SpotProductAPIServiceImpl spotProductAPIService(APIConfiguration config) {
        return new SpotProductAPIServiceImpl(config);
    }
	@Bean
	public SpotOrderApiServiceImpl spotOrderApiService(APIConfiguration config) {
		return new SpotOrderApiServiceImpl(config);
	}
}
