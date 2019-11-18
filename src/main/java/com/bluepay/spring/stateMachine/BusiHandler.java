package com.bluepay.spring.stateMachine;

import com.bluepay.spring.stateMachine.enums.BusiTypeEnum;
import com.bluepay.spring.stateMachine.enums.NodeEnum;

/**
 * 业务审批处理器
 */
public interface BusiHandler {
    /**
     * 申请
     * @return
     */
    NodeEnum apply();

    /**
     * 审批通过
     * @return
     */
    NodeEnum pass(NodeEnum currentNode);

    /**
     * 审批拒绝
     * @return
     */
    NodeEnum reject(NodeEnum currentNode);

    BusiTypeEnum getBusiType();

    /**
     * 退回补充资料
     * @return
     */
    NodeEnum sendBack(NodeEnum currentNode);

}
