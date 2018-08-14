package com.bluepay.spring.demo.thread.singleton;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * Created by ping.chen on 2018/8/11.
 */
public class SingletonTest {
    private SingletonDemo singleton;
    private int threadNum = 100;

    @Test
    public void singletonTest() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        TestRunnable runnable = new TestRunnable(countDownLatch);
        for (int i = 0; i < threadNum; i++) {
            new Thread(runnable).start();
        }
        countDownLatch.await();
        Assert.assertEquals("检测到多个实例，单例测试失败！", singleton.getInstanceCount(), 1);
    }

    private class TestRunnable implements Runnable{
        CountDownLatch countDownLatch;
        public TestRunnable(CountDownLatch countDownLatch){
            this.countDownLatch = countDownLatch;
        }
        @Override
        public void run() {
            singleton = SingletonDemo.getInstance();
            countDownLatch.countDown();
        }
    }
}
