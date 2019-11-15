package com.bluepay.spring.stateMachine.busiHandler.nodeHandler;

import com.bluepay.spring.stateMachine.BaseNodeHandler;
import com.bluepay.spring.stateMachine.enums.AuditResultEnum;
import com.bluepay.spring.stateMachine.enums.NodeEnum;

public class DirectLeaderHandler implements BaseNodeHandler {
    @Override
    public NodeEnum getNextNode(AuditResultEnum auditResult) {
        return null;
    }
}
