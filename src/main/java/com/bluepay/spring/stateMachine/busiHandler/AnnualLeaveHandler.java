package com.bluepay.spring.stateMachine.busiHandler;

import com.bluepay.spring.stateMachine.BaseBusiHandler;
import com.bluepay.spring.stateMachine.enums.AuditResultEnum;
import com.bluepay.spring.stateMachine.enums.NodeEnum;

/**
 * 年假
 */
public class AnnualLeaveHandler implements BaseBusiHandler {
    @Override
    public void audit(NodeEnum node, AuditResultEnum auditResult) {
        System.out.println("年假申请审批");

    }
}
