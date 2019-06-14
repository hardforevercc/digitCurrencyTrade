package com.okex;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@ComponentScan(basePackages = {"com.okex"})
@MapperScan(value = "com.okex.config")
@EnableScheduling
public class OkexTrandeApplication {
	private static final Logger LOG = LoggerFactory.getLogger(OkexTrandeApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(OkexTrandeApplication.class, args);
		LOG.info("[===okex-server starting==]");
		
	}
}
