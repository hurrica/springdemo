package com.ping.chen.spring.cache.ehcache;

import com.ping.chen.spring.model.JobConfig;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;


import java.lang.reflect.Field;
import java.util.*;

public class Demo {
    public CacheManager cacheManager(){
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .withCache("preConfigured",
                        CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class, ResourcePoolsBuilder.heap(10)))
                .build();
        cacheManager.init();
        return cacheManager;
    }

    public static void main(String[] args) {
        String ids = "6464391620086206464,";
        List<String> stringList = Arrays.asList(ids.split(","));
        System.out.println(stringList.size());
    }

    private static void test1() throws IllegalAccessException {
        Map<String, Field> fieldMap = filedMap(JobConfig.class);
        JobConfig jobConfig = new JobConfig();
        Field field = fieldMap.get("jobId");
        field.setAccessible(true);
        field.set(jobConfig, "test");
        System.out.println(jobConfig.getJobId());
    }

    private static Map<String, Field> filedMap(Class clazz){
        Field[] fields= clazz.getDeclaredFields();
        Map<String, Field> fieldMap = new HashMap<>(fields.length);
        for(int i=0;i<fields.length;i++){
            fieldMap.put(fields[i].getName(), fields[i]);
        }
        return fieldMap;
    }
}
