package com.ping.chen.spring.stateMachine;

import com.ping.chen.spring.stateMachine.enums.NodeEnum;

public abstract class AbstractBusiHandler implements BusiHandler{
    /**
     * 申请
     * @return
     */
    @Override
    public NodeEnum apply(){
        System.out.println(getBusiType().getEvent() + ":申请提交");
        return NodeEnum.DIRECT_LEADER;
    }

    /**
     * 审批通过
     * @return
     */
    @Override
    public NodeEnum pass(NodeEnum currentNode) {
        if (currentNode == null){
            throw new RuntimeException("当前节点不能为空");
        }
        System.out.println(getBusiType().getEvent() + "：" + currentNode.getNodeName() + "通过");
        return doPass(currentNode);
    }

    abstract protected NodeEnum doPass(NodeEnum currentNode);

    /**
     * 审批拒绝
     * @return
     */
    @Override
    public NodeEnum reject(NodeEnum currentNode){
        System.out.println(getBusiType().getEvent() + ":" + currentNode.getNodeName() + "：拒绝");
        return NodeEnum.COMPLETE;
    }

    /**
     * 退回补充资料
     * @return
     */
    @Override
    public NodeEnum sendBack(NodeEnum currentNode){
        System.out.println(getBusiType().getEvent() + ":" + currentNode.getNodeName() + "：退回");
        return NodeEnum.APPLY_COMMIT;
    }
}
