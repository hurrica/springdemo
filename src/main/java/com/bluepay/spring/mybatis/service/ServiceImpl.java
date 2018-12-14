package com.bluepay.spring.mybatis.service;

import com.bluepay.spring.model.BaseModel;
import com.bluepay.spring.mybatis.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;

public class ServiceImpl<T extends BaseModel, M extends BaseDao<T>> {
    @Autowired
    M baseMapper;

    public void test(){
        System.out.println(baseMapper);
    }
}
