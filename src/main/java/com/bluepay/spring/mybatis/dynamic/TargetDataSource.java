package com.bluepay.spring.mybatis.dynamic;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    DataSourceEnum value() default DataSourceEnum.MASTER;

}