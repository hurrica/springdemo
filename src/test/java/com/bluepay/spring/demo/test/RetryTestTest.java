package com.bluepay.spring.demo.test;

import com.bluepay.spring.Application;
import com.bluepay.spring.demo.service.impl.AsyncTest;
import lombok.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class  RetryTestTest {

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

    public static void main(String[] args) {
        List list =  Arrays.asList(111);
        System.out.println(list.get(0));
        CollectionUtils.isEmpty(list);
    }

    @Data
    static class T{
        Date  date;

    }
}
