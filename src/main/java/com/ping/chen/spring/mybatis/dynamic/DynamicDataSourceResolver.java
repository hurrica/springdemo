package com.ping.chen.spring.mybatis.dynamic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 多数据源路由解析器
 */
@Slf4j
public class DynamicDataSourceResolver extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        log.info("current data source is:{}", DynamicDataSourceContextHolder.getDataSourceType());
        return DynamicDataSourceContextHolder.getDataSourceType();
    }

    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
    }

}
