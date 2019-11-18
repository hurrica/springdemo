package com.bluepay.spring.stateMachine.enums;

/**
 * 审批节点
 */
public enum  NodeEnum {
    APPLY_COMMIT("申请提交"),
    DIRECT_LEADER("直属领导审批"),
    DEPARTMENT_MANAGER("部门经理审批"),
    DEPARTMENT_GENERAL_MANAGER("部门总经理审批"),
    HR("HR审批"),
    CEO("CEO审批"),
    COMPLETE("完成")
    ;

    private String nodeName;

    NodeEnum(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeName() {
        return nodeName;
    }
}
