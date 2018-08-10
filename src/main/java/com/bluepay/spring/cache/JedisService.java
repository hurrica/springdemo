package com.bluepay.spring.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Objects;

@Component
public class JedisService {
    @Autowired
    private JedisPool jedisPool;

    public Long ttl(String key) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.ttl(key);
        } finally {
            closeJedis(jedis);
        }
    }

    private void closeJedis(Jedis jedis) {
        if (Objects.nonNull(jedis)){
            jedis.close();
        }
    }

    public String set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.set(key, value);
        } finally {
            closeJedis(jedis);
        }
    }

    public String set(String key, String value, String setIfNotExist, String setWithExpireTime, int expiredTime) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.set(key, value, setIfNotExist, setWithExpireTime, expiredTime);
        } finally {
            closeJedis(jedis);
        }
    }

    public Object eval(String script, int valueCount, String... params) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.eval(script, valueCount, params);
        } finally {
            closeJedis(jedis);
        }
    }

    /**
     * 判断当前key的有效时间是否大于maxEffectiveTime
     * @param key
     * @param maxEffectiveTime
     * @return
     */
    public boolean checkTtl(String key, int maxEffectiveTime) {
        Jedis jedis = jedisPool.getResource();
        try {
            System.out.println(jedis.ttl(key) + "   " + maxEffectiveTime);
            return jedis.ttl(key) > maxEffectiveTime;
        } finally {
            closeJedis(jedis);
        }
    }

    public String get(String key){
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.get(key);
        } finally {
            closeJedis(jedis);
        }
    }

    public Long del(String key){
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.del(key);
        } finally {
            closeJedis(jedis);
        }
    }

    public String setExp(String key, String value, int expireTime) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.setex(key, expireTime, value);
        } finally {
            closeJedis(jedis);
        }
    }
}
