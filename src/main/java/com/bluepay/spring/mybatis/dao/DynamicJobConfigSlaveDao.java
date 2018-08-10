package com.bluepay.spring.mybatis.dao;

import com.bluepay.spring.model.ShoppingTradeInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DynamicJobConfigSlaveDao {
    @Select("SELECT * FROM shopping_trade_info limit 10")
    List<ShoppingTradeInfo> listConfig();
}
