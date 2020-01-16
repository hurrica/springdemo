package com.ping.chen.spring.source.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CirculateRefB {

    @Autowired
    private CirculateRefA circulateRefA;

    public CirculateRefA getCirculateRefA() {
        return circulateRefA;
    }
}
