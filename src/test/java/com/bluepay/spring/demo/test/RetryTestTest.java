package com.bluepay.spring.demo.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RetryTestTest {

    @Autowired
    RetryTest retryTest;

    @Test
    public void testRetry() throws Exception {
        System.out.println(retryTest.testRetry());
    }
}
