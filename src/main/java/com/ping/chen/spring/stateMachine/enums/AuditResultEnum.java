package com.ping.chen.spring.stateMachine.enums;

/**
 * 审批结果
 */
public enum AuditResultEnum {

    PASS("审批通过"),
    REJECT("审批拒绝"),
    SEND_BACK("审批退回");

    private String result;

    AuditResultEnum(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}

