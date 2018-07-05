package com.bluepay.spring.zookeeper;

import com.bluepay.spring.zookeeper.watch.MyWatch;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ZookeeperApplication {

    class MyDataCallback implements AsyncCallback.DataCallback{

        @Override
        public void processResult(int i, String s, Object o, byte[] bytes, Stat stat) {

        }
    }

    public static void main(String[] args) throws IOException {
        ZooKeeper zooKeeper = new ZooKeeper("192.168.4.238:2181", 5000, new MyWatch());
        try {
            String path = "/test/testData";
            Stat stat = zooKeeper.exists(path, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    System.out.println(Thread.currentThread().getName() + "exist watch trigger");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "---------------");
                }
            });
            if (Objects.isNull(stat)){
                System.out.println("创建节点：" + path);
                zooKeeper.create(path, "111".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
            System.out.println("修改节点内容：" + path);
            zooKeeper.setData(path, "test".getBytes(), -1);
            System.out.println("获取节点内容：" + new String (zooKeeper.getData(path, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    System.out.println(Thread.currentThread().getName() + "get data watch trigger ..");
                }
            }, stat)));
            System.out.println("修改节点内容：" + path);
            zooKeeper.setData(path, "222".getBytes(), -1);
            System.out.println("获取节点内容：" + new String (zooKeeper.getData(path, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    System.out.println(Thread.currentThread().getName() + "get data ...");
                }
            }, stat)));
            zooKeeper.delete(path, -1);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
