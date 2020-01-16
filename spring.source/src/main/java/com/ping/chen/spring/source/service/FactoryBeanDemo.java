package com.ping.chen.spring.source.service;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Service;

@Service
public class FactoryBeanDemo implements FactoryBean {
    @Override
    public Object getObject() {
        return new CirculateRefA();
    }

    @Override
    public Class<?> getObjectType() {
        return CirculateRefA.class;
    }
}
