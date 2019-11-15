package com.bluepay.spring.stateMachine;

import com.bluepay.spring.stateMachine.enums.BusiTypeEnum;
import com.bluepay.spring.stateMachine.busiHandler.AnnualLeaveHandler;
import com.bluepay.spring.stateMachine.busiHandler.PrivateAffairLeaveHandler;
import com.bluepay.spring.stateMachine.busiHandler.SickLeaveHandler;

public class BusiHandlerFactory {
    public static BaseBusiHandler getHandler(BusiTypeEnum busiType) {
        if (busiType == null) {
            throw new RuntimeException("业务类型不能为空！");
        }
        switch (busiType) {
            case SICK_LEAVE:
                return new SickLeaveHandler();
            case ANNUAL_LEAVE:
                return new AnnualLeaveHandler();
            case PRIVATE_AFFAIR_LEAVE:
                return new PrivateAffairLeaveHandler();
            default:
                throw new RuntimeException("不存在的业务类型！");
        }
    }
}
