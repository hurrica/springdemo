package com.bluepay.spring.test;

public interface TestInterface<T extends TestInterface> {
    T getInstance();
}
