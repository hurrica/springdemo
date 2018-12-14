package com.bluepay.spring.mybatis.config;

import org.apache.ibatis.type.EnumTypeHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MyEnumTypeHandler<E extends Enum<E>> extends EnumTypeHandler<E> {
    public MyEnumTypeHandler(Class type) {
        super(type);
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return super.getNullableResult(rs, columnIndex);
    }
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        System.out.println("test");
       return super.getNullableResult(rs, columnName);
    }
}
