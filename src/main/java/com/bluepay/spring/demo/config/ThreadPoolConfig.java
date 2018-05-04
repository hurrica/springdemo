package com.bluepay.spring.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class ThreadPoolConfig {
    /**
     * 线程池工作的线程总数
     *
     *
     * Set the ThreadPoolExecutor's core pool size.
     */
    private int corePoolSize = Runtime.getRuntime().availableProcessors() * 200;
    /**
     * 线程池工作极限的告警数量值，如果达到极限值并且工作队列已满,将按照策略执行
     * Set the ThreadPoolExecutor's maximum pool size.
     */
    private int maxPoolSize = Runtime.getRuntime().availableProcessors() * 200;
    /**
     * 持有等待执行的任务队列.
     * Set the capacity for the ThreadPoolExecutor's BlockingQueue.
     */
    private int queueCapacity = Runtime.getRuntime().availableProcessors() * 500;

    private final String threadNamePrefix_data = "data-load-executor-";

    private final String threadNamePrefix = "apply-pay-executor-";

    @Bean
    public Executor taskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix(threadNamePrefix_data);
        //空闲线程的存活时间.
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        /**
         * Reject策略预定义有四种：
         (1)ThreadPoolExecutor.AbortPolicy策略，是默认的策略,处理程序遭到拒绝将抛出运行时 RejectedExecutionException。
         (2)ThreadPoolExecutor.CallerRunsPolicy策略 ,调用者的线程会执行该任务,如果执行器已关闭,则丢弃.
         (3)ThreadPoolExecutor.DiscardPolicy策略，不能执行的任务将被丢弃.
         (4)ThreadPoolExecutor.DiscardOldestPolicy策略，如果执行程序尚未关闭，则位于工作队列头部的任务将被删除，然后重试执行程序（如果再次失败，则重复此过程）.
         */
        executor.setRejectedExecutionHandler(new java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    @Bean
    public ThreadPoolTaskExecutor applyPayExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix(threadNamePrefix);
        //空闲线程的存活时间.秒
        executor.setKeepAliveSeconds(15);

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        /**
         * Reject策略预定义有四种：
         (1)ThreadPoolExecutor.AbortPolicy策略，是默认的策略,处理程序遭到拒绝将抛出运行时 RejectedExecutionException。
         (2)ThreadPoolExecutor.CallerRunsPolicy策略 ,调用者的线程会执行该任务,如果执行器已关闭,则丢弃.
         (3)ThreadPoolExecutor.DiscardPolicy策略，不能执行的任务将被丢弃.
         (4)ThreadPoolExecutor.DiscardOldestPolicy策略，如果执行程序尚未关闭，则位于工作队列头部的任务将被删除，然后重试执行程序（如果再次失败，则重复此过程）.
         */
        executor.setRejectedExecutionHandler(new java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}
