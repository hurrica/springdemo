package com.ping.chen.spring.test;

public interface TestInterface<T extends TestInterface> {
    T getInstance();
}
