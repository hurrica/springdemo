/*
package com.bluepay.spring.demo.elasticjob.config;

import com.bluepay.spring.demo.elasticjob.job.MyElasticJob;
import com.bluepay.spring.demo.elasticjob.job.TestElasticJob01;
import com.bluepay.spring.demo.elasticjob.job.TestElasticJob03;
import com.bluepay.spring.demo.elasticjob.listener.MyElasticJobListener;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.event.rdb.JobEventRdbConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class ElasticJobConfig {

    private String zkServer = "192.168.4.238:2181";

    private String nameSpace = "test-elastic-job";

    @Qualifier("dataSource")
    DataSource dataSource;

    @Bean(initMethod = "init")
    public ZookeeperRegistryCenter registryCenter(){
        ZookeeperConfiguration zookeeperConfiguration = new ZookeeperConfiguration(zkServer, nameSpace);
        zookeeperConfiguration.setConnectionTimeoutMilliseconds(1000);
        zookeeperConfiguration.setMaxSleepTimeMilliseconds(3000);
        zookeeperConfiguration.setMaxRetries(3);
        return new ZookeeperRegistryCenter(zookeeperConfiguration);
    }

    */
/*@Bean(initMethod = "init")
    public JobScheduler myJob(){
        ZookeeperRegistryCenter registryCenter = registryCenter();
        JobEventConfiguration jobEventRdbConfig = new JobEventRdbConfiguration(dataSource);
        JobCoreConfiguration jobCoreConfiguration = JobCoreConfiguration.newBuilder("myElasticJob", "0/5 * * * * ?", 1).build();
        SimpleJobConfiguration simpleJobConfiguration = new SimpleJobConfiguration(jobCoreConfiguration, MyElasticJob.class.getCanonicalName());
        LiteJobConfiguration liteJobConfiguration = LiteJobConfiguration.newBuilder(simpleJobConfiguration).build();
        return new SpringJobScheduler(new MyElasticJob(), registryCenter, liteJobConfiguration, jobEventRdbConfig);
    }

    @Bean(initMethod = "init")
    public JobScheduler testJob01(){
        ZookeeperRegistryCenter registryCenter = registryCenter();
        JobEventConfiguration jobEventRdbConfig = new JobEventRdbConfiguration(dataSource);
        JobCoreConfiguration jobCoreConfiguration = JobCoreConfiguration.newBuilder("testJob01", "0/5 * * * * ?", 1).build();
        SimpleJobConfiguration simpleJobConfiguration = new SimpleJobConfiguration(jobCoreConfiguration, MyElasticJob.class.getCanonicalName());
        LiteJobConfiguration liteJobConfiguration = LiteJobConfiguration.newBuilder(simpleJobConfiguration).build();
        return new SpringJobScheduler(new TestElasticJob01(), registryCenter, liteJobConfiguration, jobEventRdbConfig);
    }

    @Bean(initMethod = "init")
    public JobScheduler testJob02(){
        ZookeeperRegistryCenter registryCenter = registryCenter();
        JobEventConfiguration jobEventRdbConfig = new JobEventRdbConfiguration(dataSource);
        JobCoreConfiguration jobCoreConfiguration = JobCoreConfiguration.newBuilder("testJob02", "0/5 * * * * ?", 1).build();
        SimpleJobConfiguration simpleJobConfiguration = new SimpleJobConfiguration(jobCoreConfiguration, MyElasticJob.class.getCanonicalName());
        LiteJobConfiguration liteJobConfiguration = LiteJobConfiguration.newBuilder(simpleJobConfiguration).build();
        return new SpringJobScheduler(new TestElasticJob02(), registryCenter, liteJobConfiguration, jobEventRdbConfig);
    }*//*


    @Bean(initMethod = "init")
    public JobScheduler testJob03(){
        ZookeeperRegistryCenter registryCenter = registryCenter();
        JobEventConfiguration jobEventRdbConfig = new JobEventRdbConfiguration(dataSource);
        JobCoreConfiguration jobCoreConfiguration = JobCoreConfiguration.newBuilder("testJob03", "0/5 * * * * ?", 3).build();
        SimpleJobConfiguration simpleJobConfiguration = new SimpleJobConfiguration(jobCoreConfiguration, MyElasticJob.class.getCanonicalName());
        LiteJobConfiguration liteJobConfiguration = LiteJobConfiguration.newBuilder(simpleJobConfiguration).build();
        return new SpringJobScheduler(new TestElasticJob03(), registryCenter, liteJobConfiguration, jobEventRdbConfig, new MyElasticJobListener());
    }

    @Bean(initMethod = "init")
    public JobScheduler testJob01(){
        ZookeeperRegistryCenter registryCenter = registryCenter();
        JobEventConfiguration jobEventRdbConfig = new JobEventRdbConfiguration(dataSource);
        JobCoreConfiguration jobCoreConfiguration = JobCoreConfiguration.newBuilder("testJob01", "0/5 * * * * ?", 1).build();
        SimpleJobConfiguration simpleJobConfiguration = new SimpleJobConfiguration(jobCoreConfiguration, MyElasticJob.class.getCanonicalName());
        LiteJobConfiguration liteJobConfiguration = LiteJobConfiguration.newBuilder(simpleJobConfiguration).build();
        return new SpringJobScheduler(new TestElasticJob01(), registryCenter, liteJobConfiguration, jobEventRdbConfig);
    }
}
*/
