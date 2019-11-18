package com.bluepay.spring.stateMachine.enums;

/**
 * 请假类型
 */
public enum BusiTypeEnum {

    ANNUAL_LEAVE("年假"),
    PRIVATE_AFFAIR_LEAVE("事假"),
    SICK_LEAVE("事假");

    private String event;

    BusiTypeEnum(String event) {
        this.event = event;
    }

    public String getEvent() {
        return event;
    }
}
