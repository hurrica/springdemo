package com.bluepay.spring.model;

import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class TsAsset {
    @Id
    private Long id;
    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 资产到期日
     */
    private Date endDate;

    /**
     * 资产地址
     */
    private String address;


    /**
     * 业务编号
     */
    private String busiKey;

    /**
     * 原资产编码
     */
    private String sourceNo;

    /**
     * 前流转资产id
     */
    private Long preAssetId;

    /**
     * 持有企业id
     */
    private Long companyId;

    /**
     * 持有账户地址
     */
    private String actAddress;
}
