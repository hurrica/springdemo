package com.ping.chen.spring.demo.thread.singleton;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 单例模式
 */
public class SingletonDemo {
    private AtomicInteger instanceCount= new AtomicInteger(0);
    private SingletonDemo(){
        instanceCount.incrementAndGet();
    }

    private static class Instance{
        private static SingletonDemo instance = new SingletonDemo();
    }

    public static SingletonDemo getInstance(){
        return Instance.instance;
    }

    public int getInstanceCount(){
        return instanceCount.get();
    }
}
