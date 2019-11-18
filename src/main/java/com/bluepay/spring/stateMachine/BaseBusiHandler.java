package com.bluepay.spring.stateMachine;

import com.bluepay.spring.stateMachine.enums.BusiTypeEnum;
import com.bluepay.spring.stateMachine.enums.NodeEnum;

/**
 * 业务审批处理器
 */
public interface BaseBusiHandler {
    /**
     * 申请
     * @return
     */
    default NodeEnum apply(){
        System.out.println(getBusiType().getEvent() + ":申请提交");
        return NodeEnum.DIRECT_LEADER;
    }

    /**
     * 审批通过
     * @return
     */
    default NodeEnum pass(NodeEnum currentNode) {
        if (currentNode == null){
            throw new RuntimeException("当前节点不能为空");
        }
        System.out.println(getBusiType().getEvent() + "：" + currentNode.getNodeName() + "通过");
        return doPass(currentNode);
    }

    NodeEnum doPass(NodeEnum currentNode);

    /**
     * 审批拒绝
     * @return
     */
    default NodeEnum reject(NodeEnum currentNode){
        System.out.println(getBusiType().getEvent() + ":" + currentNode.getNodeName() + "：拒绝");
        return NodeEnum.COMPLETE;
    }

    BusiTypeEnum getBusiType();

    /**
     * 退回补充资料
     * @return
     */
    default NodeEnum sendBack(NodeEnum currentNode){
        System.out.println(getBusiType().getEvent() + ":" + currentNode.getNodeName() + "：退回");
        return NodeEnum.APPLY_COMMIT;
    }

}
