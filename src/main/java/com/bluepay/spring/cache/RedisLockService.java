package com.bluepay.spring.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RedisLockService {
    /*@Autowired
    private JedisService jedisService;

    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "EX";
    private static final Long RELEASE_SUCCESS = 1L;

    public String getRequestId(String id){
        return id + "_" + System.currentTimeMillis();
    }

    public boolean tryGetRedisLock(String key, String value, int expiredTime){
        jedisService.del(key);
        String result = jedisService.set(key, value, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expiredTime);
        if (LOCK_SUCCESS.equals(result)){
            return true;
        }
        return false;
    }

    public boolean releaseRedisLock(String key, String value){
        String script = "if redis.call('get', KEYS[1]) == KEYS[2] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedisService.eval(script, 2, key, value);
        if (RELEASE_SUCCESS.equals(result)){
            return true;
        }
        return false;
    }*/
}
