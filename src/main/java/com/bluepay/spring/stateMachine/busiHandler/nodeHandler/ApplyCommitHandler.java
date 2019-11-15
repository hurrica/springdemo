package com.bluepay.spring.stateMachine.busiHandler.nodeHandler;

import com.bluepay.spring.stateMachine.BaseNodeHandler;
import com.bluepay.spring.stateMachine.enums.AuditResultEnum;
import com.bluepay.spring.stateMachine.enums.NodeEnum;

public class ApplyCommitHandler implements BaseNodeHandler {
    @Override
    public NodeEnum getNextNode(AuditResultEnum auditResult) {
        switch (auditResult){
            case PASS:
                default: throw new RuntimeException(NodeEnum.APPLY_COMMIT.getNodeName() + "不支持" + auditResult.getResult());
        }
    }
}
