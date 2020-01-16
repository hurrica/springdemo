package com.ping.chen.spring.stateMachine;

import com.ping.chen.spring.stateMachine.enums.NodeEnum;

import java.util.Random;

public class ApplyTest {
    public static void main(String[] args) {
    }

    private static void applyTest() {
        Random random = new Random();
        BusiHandler handler = BusiHandlerFactory.getHandler();
        System.out.println("----------------------");
        NodeEnum nextNode = handler.apply();
        System.out.println();

        while (nextNode != NodeEnum.COMPLETE) {
            if (random.nextInt(12) > 11) {
                nextNode = handler.sendBack(nextNode);
            } else if (random.nextInt(12) > 10) {
                nextNode = handler.reject(nextNode);
            } else {
                nextNode = handler.pass(nextNode);
            }
        }
        System.out.println("******************");
    }
}
