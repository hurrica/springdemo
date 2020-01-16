package com.ping.chen.spring.stateMachine;

import com.ping.chen.spring.stateMachine.enums.BusiTypeEnum;
import com.ping.chen.spring.stateMachine.busiHandler.AnnualLeaveHandler;
import com.ping.chen.spring.stateMachine.busiHandler.PrivateAffairLeaveHandler;
import com.ping.chen.spring.stateMachine.busiHandler.SickLeaveHandler;

import java.util.Random;

public class BusiHandlerFactory {
    public static BusiHandler getHandler(BusiTypeEnum busiType) {
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

    public static BusiHandler getHandler() {
        Random rd = new Random();
        int random = rd.nextInt(12);
        if (random > 8) {
            return new SickLeaveHandler();
        } else if (random > 4){
            return new AnnualLeaveHandler();
        } else {
            return new PrivateAffairLeaveHandler();
        }
    }
}