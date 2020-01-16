package com.ping.chen.spring.stateMachine.busiHandler;

import com.ping.chen.spring.stateMachine.AbstractBusiHandler;
import com.ping.chen.spring.stateMachine.enums.BusiTypeEnum;
import com.ping.chen.spring.stateMachine.enums.NodeEnum;

/**
 * 年假
 * 审批路径 直属领导审批->部门经理审批->HR->结束
 */
public class AnnualLeaveHandler extends AbstractBusiHandler {

    @Override
    public NodeEnum doPass(NodeEnum currentNode) {

        switch (currentNode){
            case APPLY_COMMIT:
                return NodeEnum.DIRECT_LEADER;
            case DIRECT_LEADER:
                return NodeEnum.DEPARTMENT_MANAGER;
            case DEPARTMENT_MANAGER:
                return NodeEnum.HR;
            case HR:
                return NodeEnum.COMPLETE;
                default:
                    throw new RuntimeException("年假审批不支持当前审批类型！");
        }
    }

    @Override
    public BusiTypeEnum getBusiType() {
        return BusiTypeEnum.ANNUAL_LEAVE;
    }


}
