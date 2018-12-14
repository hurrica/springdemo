package com.bluepay.spring.mybatis.config;

import com.bluepay.spring.mybatis.demo.BaseEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeException;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyBaseTypeHandler<E extends BaseEnum<E>> extends BaseTypeHandler<BaseEnum<E>> {
    private final Class<E> type;

    public MyBaseTypeHandler(Class<E> type){
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, BaseEnum<E> parameter, JdbcType jdbcType) throws SQLException {
        if (jdbcType == null) {
            ps.setString(i, parameter.getDickKey());
        } else {
            ps.setObject(i, parameter.getDickKey(), jdbcType.TYPE_CODE); // see r3589
        }
    }

    @Override
    public BaseEnum<E> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String s = rs.getString(columnName);
        return s == null ? null : Enum.valueOf(type, s);
    }

    @Override
    public BaseEnum<E> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public BaseEnum<E> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }
}
