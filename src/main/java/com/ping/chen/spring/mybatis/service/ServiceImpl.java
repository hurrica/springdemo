package com.ping.chen.spring.mybatis.service;

import com.ping.chen.spring.model.BaseModel;
import com.ping.chen.spring.mybatis.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;

public class ServiceImpl<T extends BaseModel, M extends BaseDao<T>> {
    @Autowired
    M baseMapper;

    public void test(){
        System.out.println(baseMapper);
    }
}
