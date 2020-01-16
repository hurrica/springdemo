package com.ping.chen.spring.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 任务配置
 */
@Data
@Table(name = "job_config")
public class JobConfig extends BaseModel{
    @Id
    private Integer id;

    private String jobId;// 任务id 不能修改

    private String cronTab;//时间表达式

    private String jobClass;// 任务执行类名 不能修改

    private String description;//任务描述

    private String dataSource;//数据源

    private String selectSql;//查询sql

    private String executeParam;//执行参数配置
}
