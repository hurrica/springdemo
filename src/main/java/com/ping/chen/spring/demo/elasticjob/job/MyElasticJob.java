package com.ping.chen.spring.demo.elasticjob.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

public class MyElasticJob implements SimpleJob {
    @Override
    public void execute(ShardingContext shardingContext) {
        switch (shardingContext.getShardingItem()){
            case 0:
                System.out.println("hello elastic job " );
                break;
            case 1:
                System.out.println("this is my job");
                break;
            case 2:
                System.out.println("then, what next");
                break;
        }
    }
}
