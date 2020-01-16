package com.ping.chen.spring.zookeeper.watch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

public class MyWatch implements Watcher {
    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("触发监听事件" + watchedEvent.getType());
    }
}
