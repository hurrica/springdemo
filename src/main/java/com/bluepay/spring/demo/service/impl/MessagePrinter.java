package com.bluepay.spring.demo.service.impl;

import com.bluepay.spring.demo.service.MessageService;
import com.bluepay.spring.demo.test.RetryTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class MessagePrinter {
    @Autowired
    private MessageService service;

    @Autowired
    RetryTest retryTest;

    public void printMessage() {
        System.out.println(this.service.getMessage());
    }

   /* @Async("taskExecutor")*/
    public void testRetry() throws Exception {
        retryTest.testRetry();
    }
}
