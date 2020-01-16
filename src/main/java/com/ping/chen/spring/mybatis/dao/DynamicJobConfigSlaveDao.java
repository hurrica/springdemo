package com.ping.chen.spring.mybatis.dao;

import com.ping.chen.spring.mybatis.demo.TsTransaction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DynamicJobConfigSlaveDao {
    @Select("SELECT * FROM ts_transaction limit 10")
    List<TsTransaction> listConfig();
}
