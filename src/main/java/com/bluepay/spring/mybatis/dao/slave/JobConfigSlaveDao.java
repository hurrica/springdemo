package com.bluepay.spring.mybatis.dao.slave;

import com.bluepay.spring.model.ShoppingTradeInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface JobConfigSlaveDao {
    @Select("SELECT * FROM shopping_trade_info limit 10")
    List<ShoppingTradeInfo> listConfig();
}
