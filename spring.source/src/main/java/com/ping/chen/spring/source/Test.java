package com.ping.chen.spring.source;

import com.ping.chen.spring.source.service.FactoryBeanDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    @Autowired
    private TestInterface testInterface;

    public static void main(String[] args) {

        ApplicationContext app = new ClassPathXmlApplicationContext("spring.xml");
    }

    public static void test(){
        ApplicationContext context = new AnnotationConfigApplicationContext("com.ping.chen.spring.source");

        System.out.println(context.getBean("&factoryBeanDemo"));
        System.out.println(context.getBean(FactoryBeanDemo.class));
        System.out.println(context.getBean("factoryBeanDemo"));
        System.out.println(context.getBean("factoryBeanDemo"));
        System.out.println(context.getBean("circulateRefA"));
        System.out.println(context.getBean("circulateRefA"));
    }
}
