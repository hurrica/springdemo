package com.ping.chen.spring.mybatis.dao.master;

import com.ping.chen.spring.mybatis.demo.TransactionType;
import com.ping.chen.spring.mybatis.demo.TsTransaction;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TsTransactionMapper {
    List<TsTransaction> listAll(@Param("transactionType") TransactionType cash);
}
