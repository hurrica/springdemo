package com.ping.chen.spring.mybatis.dynamic;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor
@Getter
public enum DataSourceEnum {
    MASTER("master", "主数据源"),
    SLAVE("slave", "slave")
    ;

    private String dataSource;//数据源
    private String description;//数据源说明

    public static DataSourceEnum get(String dataSource){
        Optional<DataSourceEnum> optional = Arrays.stream(values()).filter(dataSourceEnum -> dataSourceEnum.dataSource.equals(dataSource)).findAny();
        if (optional.isPresent()){
            return optional.get();
        }
        return null;
    }
}
