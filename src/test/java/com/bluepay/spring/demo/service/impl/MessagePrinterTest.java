package com.bluepay.spring.demo.service.impl;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessagePrinterTest {

    @Autowired
    MessagePrinter messagePrinter;

    @Test
    public void printMessage() throws Exception {
        messagePrinter.testRetry();
        System.out.println(messagePrinter);
    }
}
