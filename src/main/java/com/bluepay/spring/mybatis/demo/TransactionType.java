package com.bluepay.spring.mybatis.demo;

import java.util.Arrays;
import java.util.Optional;

/**
 * @Type TransactionType.java
 * @Desc 交易类型
 * @author fengsibo
 * @date 2017年12月25日 下午4:52:18
 * @version 
 */
public enum TransactionType implements BaseEnum<TransactionType, String> {

    ISSUE("发行", "issue"),
    TRANSFER("转让", "transfer"),
    CASH("融资", "cash"),
    SETTLE("兑付", "settle");

    private String dictKey;

    private String displayName;

    TransactionType(String displayName, String dictKey) {
        this.dictKey = dictKey;
        this.displayName = displayName;
    }

    @Override
    public String getDickKey() {
        return this.dictKey;
    }

    @Override
    public BaseEnum<TransactionType, String> toBaseEnum(String s) {
        Optional<TransactionType> optional = Arrays.stream(values()).filter(transactionType -> transactionType.getDickKey().endsWith(s)).findAny();
        if (optional.isPresent()){
            return optional.get();
        }
        return null;
    }
}
