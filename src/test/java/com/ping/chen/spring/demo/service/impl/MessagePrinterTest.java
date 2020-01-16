package com.ping.chen.spring.demo.service.impl;


import com.ping.chen.spring.demo.service.MessageService;
import com.ping.chen.spring.model.TestModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessagePrinterTest {

    @Autowired
    MessagePrinter messagePrinter;

    @Autowired
    MessageService messageService;

    @Test
    public void printMessage() throws Exception {
        TestModel testModel = new TestModel();
        testModel.setDescription("test generic");
        messageService.genericTest(testModel);
        String ss = "sadfasf";
        ss.getBytes("iso-8859-11");
        System.out.println(messageService.getMessageService("messageServiceImpl"));
        System.out.println(MessageService.serviceMap.get("messageServiceImpl"));
    }

    public static void main(String[] args) throws IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream("test.txt", true), "iso-8859-11");
            String ss = new String ("hello,ารรับชำระผ่านช่องทางอิเล็กทรอนิกส์\n".getBytes("iso-8859-11"), "iso-8859-11");
        outputStreamWriter.write(ss);
        outputStreamWriter.flush();
        outputStreamWriter.close();

        System.out.println(new String("hello,ารรับชำระผ่านช่องทางอิเล็กทรอนิกส์".getBytes("iso-8859-11")));
    }
}
