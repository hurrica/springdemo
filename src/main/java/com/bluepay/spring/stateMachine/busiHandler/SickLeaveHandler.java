package com.bluepay.spring.stateMachine.busiHandler;

import com.bluepay.spring.stateMachine.BaseBusiHandler;
import com.bluepay.spring.stateMachine.enums.BusiTypeEnum;
import com.bluepay.spring.stateMachine.enums.NodeEnum;

/**
 * 病假审批流程
 * 申请提交->直属领导审批->hr审批->部门总经理审批->ceo审批
 */
public class SickLeaveHandler implements BaseBusiHandler {
    @Override
    public NodeEnum doPass(NodeEnum currentNode) {
        switch (currentNode) {
            case APPLY_COMMIT:
                return NodeEnum.DIRECT_LEADER;
            case DIRECT_LEADER:
                return NodeEnum.HR;
            case HR:
                return NodeEnum.DEPARTMENT_MANAGER;
            case DEPARTMENT_MANAGER:
                return NodeEnum.CEO;
            case CEO:
                return NodeEnum.COMPLETE;

            default:
                throw new RuntimeException("病假不支持" + currentNode.getNodeName());
        }
    }

    @Override
    public BusiTypeEnum getBusiType() {
        return BusiTypeEnum.SICK_LEAVE;
    }
}
