package com.ping.chen.spring.source;

import org.springframework.beans.factory.support.GenericBeanDefinition;

public class BeanDefinitionTest {
    public static void main(String[] args) {
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(Test.class);
    }
}
