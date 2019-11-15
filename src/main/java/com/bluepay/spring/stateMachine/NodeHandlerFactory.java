package com.bluepay.spring.stateMachine;

import com.bluepay.spring.stateMachine.enums.NodeEnum;
import com.bluepay.spring.stateMachine.busiHandler.nodeHandler.ApplyCommitHandler;
import com.bluepay.spring.stateMachine.busiHandler.nodeHandler.DirectLeaderHandler;

public class NodeHandlerFactory {
    public static BaseNodeHandler getNodeHandler(NodeEnum node) {
        if (node == null){
            throw new RuntimeException("审批节点不能为空");
        }
        switch (node) {
            case APPLY_COMMIT:
                return new ApplyCommitHandler();
            case DIRECT_LEADER:
                return new DirectLeaderHandler();
            case DEPARTMENT_MANAGER:
            case DEPARTMENT_GENERAL_MANAGER:
            case HR:
            case CEO:
            default:
                throw new RuntimeException("不支持该审批节点：" + node.getNodeName());
        }
    }
}
