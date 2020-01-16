package com.ping.chen.spring.mybatis.demo;

/**
 * @Type TransactionType.java
 * @Desc 交易类型
 * @author fengsibo
 * @date 2017年12月25日 下午4:52:18
 * @version 
 */
public enum TransactionType implements BaseEnum<String> {

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
    public String getDictKey() {
        return this.dictKey;
    }

}
