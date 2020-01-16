package com.ping.chen.spring.stateMachine.busiHandler;

import com.ping.chen.spring.stateMachine.AbstractBusiHandler;
import com.ping.chen.spring.stateMachine.enums.BusiTypeEnum;
import com.ping.chen.spring.stateMachine.enums.NodeEnum;

/**
 * 事假申请
 * 审批流程->直属领导审批-部门经理审批->总经理->HR
 */
public class PrivateAffairLeaveHandler extends AbstractBusiHandler {

    @Override
    public NodeEnum doPass(NodeEnum currentNode) {
        switch (currentNode) {
            case APPLY_COMMIT:
                return NodeEnum.DIRECT_LEADER;
            case DIRECT_LEADER:
                return NodeEnum.DEPARTMENT_MANAGER;
            case DEPARTMENT_MANAGER:
                return NodeEnum.DEPARTMENT_GENERAL_MANAGER;
            case DEPARTMENT_GENERAL_MANAGER:
                return NodeEnum.HR;
            case HR:
                return NodeEnum.COMPLETE;
            default:
                throw new RuntimeException("事假不支持" + currentNode.getNodeName());
        }
    }

    @Override
    public BusiTypeEnum getBusiType() {
        return BusiTypeEnum.PRIVATE_AFFAIR_LEAVE;
    }
}