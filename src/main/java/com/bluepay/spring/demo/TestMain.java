package com.bluepay.spring.demo;


import org.apache.zookeeper.ZooKeeper;

public class TestMain {
    public static void main(String[] args) {
        byte[] bytes = new byte[1024*1024*20];
        System.gc();
        System.out.println(Runtime.getRuntime().maxMemory()/1024/1024);
        System.out.println(Runtime.getRuntime().freeMemory()/1024/1024);
        System.out.println(Runtime.getRuntime().totalMemory()/1024/1024);
    }
}
