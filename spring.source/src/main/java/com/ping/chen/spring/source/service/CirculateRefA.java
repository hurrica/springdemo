package com.ping.chen.spring.source.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CirculateRefA {
    @Autowired
    private CirculateRefB circulateRefB;

    public void test(){
        System.out.println(">>>>>>>>>>>>CirculateRefA>>>>>>>>>>>>");
    }
}
