package com.bluepay.spring.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {
    private TestInterface<?> testInterface;


    public MyInvocationHandler(TestInterface<?> testInterface) {
        this.testInterface = testInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return this.testInterface.getInstance();
    }
}
