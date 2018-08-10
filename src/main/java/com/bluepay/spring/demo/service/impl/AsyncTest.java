package com.bluepay.spring.demo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AsyncTest {

    @Async
    public void test(){
        System.out.println("***********************************************************");
        System.out.println("test start execute..." + "| ThreadName:"+ Thread.currentThread().getName());

        System.out.println("test complete.");
        log.info("***********************************************************");
    }

    @Async("applyPayExecutor")
    public void test2(){
        System.out.println("test2 start execute... | " + Thread.currentThread().getName());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test2 complete");
    }
}
