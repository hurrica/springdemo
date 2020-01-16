package com.ping.chen.spring.demo.service;

import java.util.HashMap;
import java.util.Map;

public interface MessageService<T> {
    Map<String, MessageService> serviceMap = new HashMap<>();
    String getMessage();
    String testRetry() throws Exception;

    void genericTest(T entity);

    void register(String serviceName, MessageService messageService);

    default MessageService getMessageService(String serviceName){
        return serviceMap.get(serviceName);
    }
}
