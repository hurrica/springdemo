package com.bluepay.spring.mybatis.dynamic;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(-1)// 保证该AOP在@Transactional之前执行
@Component
@Slf4j
public class DynamicDataSourceAspect {
    @Before("@annotation(targetDataSource)")
    public void changeDataSource(JoinPoint joinPoint, TargetDataSource targetDataSource) throws Throwable{
        DataSourceEnum dataSourceEnum = targetDataSource.value();
        log.info("method:{}, 使用数据源：{}", joinPoint.getSignature(), dataSourceEnum.getDataSource());
        DynamicDataSourceContextHolder.setDataSourceType(dataSourceEnum.getDataSource());
    }

    @After("@annotation(targetDataSource)")
    public void restoreDataSource(TargetDataSource targetDataSource){
        //DynamicDataSourceContextHolder.clearDataSourceType();
    }

}
