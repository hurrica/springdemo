package com.ping.chen.spring.demo.elasticjob.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

public class TestElasticJob03 implements SimpleJob {
    @Override
    public void execute(ShardingContext shardingContext) {
        System.out.println("test job 03");
    }
}
