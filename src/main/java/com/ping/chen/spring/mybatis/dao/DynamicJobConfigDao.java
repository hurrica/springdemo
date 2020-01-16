package com.ping.chen.spring.mybatis.dao;

import com.ping.chen.spring.model.TestModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper()
public interface DynamicJobConfigDao {
    @Select("select * from ts_transaction")
    List<TestModel> listConfig();
}
