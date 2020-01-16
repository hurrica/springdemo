package com.ping.chen.spring.mybatis.demo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 交易汇总表 实体
 * </p>
 *
 * @author fengsibo
 * @since 2018-04-03
 */
@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
public class TsTransaction {

	private static final long serialVersionUID = 4539007204006206911L;

	/**
	 * 发起方交易hash
	 */
	private String sedTransHash;

	/**
	 * 链id
	 */
	private String chainId;

	/**
	 * 接收方交易hash
	 */
	private String recTransHash;

	/**
	 * 交易资产id
	 */
	private Long assetId;

	/**
	 * 交易资产地址
	 */
	private String assetAddress;

	/**
	 * 原始资产编号
	 */
	private String sourceNo;

	/**
	 * 交易金额
	 */
	private BigDecimal amount;

	/**
	 * 费用金额
	 */
	private BigDecimal fee;

	/**
	 * 利率
	 */
	private BigDecimal rate;


	/**
	 * 发起方企业id
	 */
	private Long sedCompanyId;

	/**
	 * 发起方企业名称
	 */
	private String sedCompanyName;

	/**
	 * 发起方账户地址
	 */
	private String sedActAddress;

	/**
	 * 接收方企业id
	 */
	private Long recCompanyId;

	/**
	 * 接收方账户地址
	 */
	private String recActAddress;

	/**
	 * 接收方企业名称
	 */
	private String recCompanyName;

	/**
	 * 收费方方企业id
	 */
	private Long feeCompanyId;

	/**
	 * 收费方企业名称
	 */
	private String feeCompanyName;

	/**
	 * 收费方账户地址
	 */
	private String feeActAddress;

	/**
	 * 签收日期
	 */
	private Date signDate;

	private String note;

}
