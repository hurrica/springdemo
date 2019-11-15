package com.bluepay.spring.stateMachine.busiHandler;

import com.bluepay.spring.stateMachine.BaseBusiHandler;
import com.bluepay.spring.stateMachine.enums.AuditResultEnum;
import com.bluepay.spring.stateMachine.enums.NodeEnum;

/**
 * 病假审批流程
 * 申请提交->直属领导审批->hr审批
 */
public class SickLeaveHandler implements BaseBusiHandler {
    @Override
    public void audit(NodeEnum node, AuditResultEnum auditResult) {
        System.out.println("病假申请审批");

    }
}
