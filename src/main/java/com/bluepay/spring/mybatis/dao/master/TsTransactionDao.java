package com.bluepay.spring.mybatis.dao.master;

import com.bluepay.spring.mybatis.demo.TransactionType;
import com.bluepay.spring.mybatis.demo.TsTransaction;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TsTransactionDao {
    List<TsTransaction> listAll(@Param("transactionType") TransactionType cash);
}
