package com.bluepay.spring.demo.test;

import com.bluepay.spring.Application;
import com.bluepay.spring.demo.service.impl.AsyncTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RetryTestTest {

    @Autowired
    RetryTest retryTest;

    @Test
    public void testRetry() throws Exception {
        System.out.println(retryTest.testRetry());
    }

    @Autowired
    AsyncTest asyncTest;

    @Test
    public void asyncTest(){
        asyncTest.test();
    }
}
