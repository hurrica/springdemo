package com.ping.chen.spring.demo.test;

import com.dangdang.ddframe.job.lite.api.JobScheduler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JobSchedulerTest {

    @Test
    public void test(){
        StdSchedulerFactory factory = new StdSchedulerFactory();
        try {
            Scheduler scheduler = factory.getScheduler();
            System.out.println(scheduler.getSchedulerName());
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        //JobScheduler jobScheduler = new JobScheduler();
    }
}
