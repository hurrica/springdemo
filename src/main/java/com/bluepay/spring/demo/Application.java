package com.bluepay.spring.demo;

import com.bluepay.spring.demo.test.RetryTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableRetry
public class Application {


    public static void main(String[] args) throws Exception {

        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        RetryTest retryTest = context.getBean(RetryTest.class);
        System.out.println(">>>>>>>>>>>>");
        retryTest.testRetry();
        System.out.println(">>>>>>>>>>>>>>");
    }
}
