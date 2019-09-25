package com.bluepay.spring.mybatis.dao;

import com.bluepay.spring.model.ShoppingTradeInfo;
import com.bluepay.spring.mybatis.demo.TsTransaction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DynamicJobConfigSlaveDao {
    @Select("SELECT * FROM ts_transaction limit 10")
    List<TsTransaction> listConfig();
}
