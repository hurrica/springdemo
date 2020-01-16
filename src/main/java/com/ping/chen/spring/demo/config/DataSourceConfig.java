package com.ping.chen.spring.demo.config;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean(name = "dataSource")
    public DataSource dataSource(){
        DataSource dataSource = DataSourceBuilder
                .create()
                .driverClassName("com.mysql.jdbc.Driver")
                .url("jdbc:mysql://192.168.4.238:21411/award?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&zeroDateTimeBehavior=convertToNull&useSSL=false")
                .username("zcz")
                .password("CcTsCtbT0DF6yePeMjkNbuNfJxFQDb4a")
                .type(MysqlConnectionPoolDataSource.class)
                .build();
        return dataSource;
    }

}
