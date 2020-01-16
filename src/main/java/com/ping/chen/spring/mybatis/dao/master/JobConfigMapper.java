package com.ping.chen.spring.mybatis.dao.master;

import com.ping.chen.spring.mybatis.demo.TsTransaction;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface JobConfigMapper {
    @Select("select * from ts_transaction limit 10")
    List<TsTransaction> listConfig();
}
