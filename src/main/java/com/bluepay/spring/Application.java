package com.bluepay.spring;

import com.bluepay.spring.mybatis.service.JobConfigService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        JobConfigService jobConfigService = context.getBean(JobConfigService.class);
        jobConfigService.test();
        System.out.println(jobConfigService.listConfig());
    }
}
