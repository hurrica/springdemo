package com.bluepay.spring.test;

public class TestInterfaceImpl implements TestInterface<TestInterfaceImpl> {
    @Override
    public TestInterfaceImpl getInstance() {
        return this;
    }
}
