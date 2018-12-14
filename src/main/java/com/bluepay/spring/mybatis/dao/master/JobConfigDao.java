package com.bluepay.spring.mybatis.dao.master;

import com.bluepay.spring.mybatis.demo.TsTransaction;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface JobConfigDao {
    @Select("select * from ts_transaction limit 10")
    List<TsTransaction> listConfig();
}
