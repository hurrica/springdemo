package com.ping.chen.spring.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @Async 注解默认线程池配置
 */
@Configuration
public class AsyncTaskExecutePoolConfig extends AsyncConfigurerSupport {
    private final int coreProcessor = Runtime.getRuntime().availableProcessors();
    private final int corePoolSize = coreProcessor * 2;
    private final int maxPoolSize = coreProcessor * 100;
    private final int queueCapacity = coreProcessor * 10000;
    private final String threadNamePrefix = "spring-demo-";
    private final int keepAliveSeconds = 15;

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setThreadNamePrefix(threadNamePrefix);
        return executor;
    }

    @Override
    public Executor getAsyncExecutor(){
        return threadPoolTaskExecutor();
    }
}
