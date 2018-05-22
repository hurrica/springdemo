package com.bluepay.spring.demo.elasticjob.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

public class TestElasticJob02 implements SimpleJob {
    @Override
    public void execute(ShardingContext shardingContext) {
        System.out.println("test job 02");
    }
}
