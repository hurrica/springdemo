package com.ping.chen.spring.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 任务执行日志
 */
@Data
@Table(name = "job_execute_log")
public class JobExecuteLog {
    @Id
    private Long id;

    private String jobId;//任务id

    private Integer pushTotal;//推送总数

    private String status;//执行状态

    private String description;//状态描述

    private Date executeTime;//执行时间


    public static JobExecuteLog createLog(String jobId, String status, String description, Integer pushTotal) {
        JobExecuteLog jobExecuteLog = new JobExecuteLog();
        jobExecuteLog.setJobId(jobId);
        jobExecuteLog.setExecuteTime(new Date());
        jobExecuteLog.setStatus(status);
        jobExecuteLog.setDescription(description);
        jobExecuteLog.setPushTotal(pushTotal);
        return jobExecuteLog;
    }
}
