package com.ping.chen.spring.model;

import lombok.Data;

import javax.persistence.Table;

@Data
@Table(name = "shopping_trade_info")
public class ShoppingTradeInfo {
    private Long id;
    private String transId;
    private String userId;
}
