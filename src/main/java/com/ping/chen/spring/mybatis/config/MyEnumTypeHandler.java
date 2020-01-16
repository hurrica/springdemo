package com.ping.chen.spring.mybatis.config;

import com.ping.chen.spring.mybatis.demo.BaseEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyEnumTypeHandler<T> extends BaseTypeHandler<BaseEnum<T>> {
    private final Class<BaseEnum> type;

    public MyEnumTypeHandler(Class<BaseEnum> type) {
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, BaseEnum parameter, JdbcType jdbcType) throws SQLException {
        System.out.println("set value");
        if (jdbcType == null) {
            ps.setString(i, (String) parameter.getDictKey());
        } else {
            ps.setObject(i, parameter.getDictKey(), jdbcType.TYPE_CODE); // see r3589
        }
    }

    @Override
    public BaseEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String s = rs.getString(columnName);
        return s == null ? null : BaseEnum.valueOfEnum(type, s);
    }

    @Override
    public BaseEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String s = rs.getString(columnIndex);
        return s == null ? null : BaseEnum.valueOfEnum(type, s);
    }

    @Override
    public BaseEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String s = cs.getString(columnIndex);
        return s == null ? null : BaseEnum.valueOfEnum(type, s);
    }
}
