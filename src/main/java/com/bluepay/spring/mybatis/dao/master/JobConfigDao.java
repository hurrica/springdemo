package com.bluepay.spring.mybatis.dao.master;

import com.bluepay.spring.model.TestModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper()
public interface JobConfigDao {
    @Select("select * from trans_all_log")
    List<TestModel> listConfig();
}
