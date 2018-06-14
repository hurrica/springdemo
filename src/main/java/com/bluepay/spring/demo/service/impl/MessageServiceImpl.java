package com.bluepay.spring.demo.service.impl;

import com.bluepay.spring.demo.service.MessageService;
import com.bluepay.spring.model.TestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class MessageServiceImpl implements MessageService<TestModel> {

    public MessageServiceImpl(){
        register("messageServiceImpl", this);
    }

    @Autowired
    AsyncTest test;

    @Async("taskExecutor")
    public String getMessage() {
        System.out.println(Thread.currentThread().getName());
        test.test();
        test.test2();
        try {
            System.out.println("开始睡眠");
            Thread.sleep(2000);
            System.out.println("结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "sdgfas";
    }

    public String testRetry() throws Exception {
        System.out.println("testRetry start ...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new Exception("retry test");
    }

    @Override
    public void genericTest(TestModel entity) {
        System.out.println(entity.getMessage());
    }

    @Override
    public void register(String serviceName, MessageService messageService) {
        serviceMap.put(serviceName, messageService);
    }

}
