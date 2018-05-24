package com.bluepay.spring.demo.elasticjob.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.sohu.idcenter.IdWorker;

public class TestElasticJob01 implements SimpleJob {
    private IdWorker idWorker = new IdWorker();
    @Override
    public void execute(ShardingContext shardingContext) {
        System.out.println(idWorker.getId());
    }
}
