package com.bluepay.spring.demo.test;

import com.bluepay.spring.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

/**
 * spring @EnableRetry注解依赖于org.aspectj包，如果想要使用spring的重试机制，需要将该依赖添加到项目中
 */
@Component
public class RetryTest {
    @Autowired
    MessageService messageService;
    /***
     *
     *
     @Retryable注解 被注解的方法发生异常时会重试
     value:指定发生的异常进行重试
     include:和value一样，默认空，当exclude也为空时，所有异常都重试
     exclude:指定异常不重试，默认空，当include也为空时，所有异常都重试
     maxAttemps:重试次数，默认3
     backoff:重试补偿机制，默认没有

     @Backoff注解 delay:指定延迟后重试
     multiplier:指定延迟的倍数，比如delay=5000l,multiplier=2时，第一次重试为5秒后，第二次为10秒，第三次为20秒

     @Recover 当重试到达指定次数时，被注解的方法将被回调，可以在该方法中进行日志处理。需要注意的是发生的异常和入参类型一致时才会回调
     */
    private final static int MAXATTEMPTS = 6;

    private final static long DELAY = 1L;

    /**
     * 延迟为 第一次1分钟，第二次2分钟，第三次为4分钟，第四次为8分钟  依此类推重试12次
     */
    private final static int MULTIPLIER = 2;

    @Retryable(maxAttempts = MAXATTEMPTS)
    public void testRetry() throws Exception {
        messageService.testRetry();
    }
}
