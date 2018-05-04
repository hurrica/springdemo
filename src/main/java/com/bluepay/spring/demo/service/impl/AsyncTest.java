package com.bluepay.spring.demo.service.impl;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncTest {

    @Async("applyPayExecutor")
    public void test(){
        System.out.println("test start execute..." + "| ThreadName:"+ Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test complete.");
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
