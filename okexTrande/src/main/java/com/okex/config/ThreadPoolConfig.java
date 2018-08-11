package com.okex.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 线程池配置类
 */
@SpringBootConfiguration
public class ThreadPoolConfig {
	
	@Bean(name="threadPool")
    public ThreadPoolTaskExecutor threadPool() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(20);
        taskExecutor.setMaxPoolSize(50);
        taskExecutor.setKeepAliveSeconds(30000);
        taskExecutor.setQueueCapacity(200);
        return taskExecutor;
    }
}