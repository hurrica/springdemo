package com.ping.chen.spring.model;

import lombok.Data;

import java.util.Date;

@Data
public class TestModel {

    private Integer amount;
    //pocket_id
    private Integer pocket_id;
    //producer_id
    private Integer producer_id;
    //记录描述。
    private String description;
    private String from;
    private String ip;
    //事务ID。
    private String msisdn;
    //具体充值运营商
    private String operator;
    //充值状态。
    private String status;
    //充值运营商 1:True 2:Ais 3:Dtac
    private String telco;
    //事务ID。
    private String transaction_id;
    //充值单位。
    private String unit;
    //请求充值时间。
    private Date req_time;
    //充值响应时间。
    private Date rsp_time;
}
