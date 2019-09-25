package com.bluepay.spring.mybatis.service;


import com.bluepay.spring.Application;
import com.bluepay.spring.mybatis.dao.DynamicJobConfigDao;
import com.bluepay.spring.mybatis.dao.DynamicJobConfigSlaveDao;
import com.bluepay.spring.mybatis.dao.slave.JobConfigSlaveMapper;
import com.bluepay.spring.mybatis.dynamic.DataSourceEnum;
import com.bluepay.spring.mybatis.dynamic.DynamicDataSourceContextHolder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@TestPropertySource({"classpath:application.yml"})
public class JobConfigServiceTest {
    @Autowired
    JobConfigService jobConfigService;

    @Autowired
    JobConfigSlaveMapper jobConfigSlaveMapper;

    @Autowired
    DynamicJobConfigDao dynamicJobConfigDao;

    @Autowired
    DynamicJobConfigSlaveDao dynamicJobConfigSlaveDao;

    @Test
    public void test(){
        //System.out.println(jobConfigService.listConfig());

        DynamicDataSourceContextHolder.setDataSourceType(DataSourceEnum.SLAVE.getDataSource());
        System.out.println(dynamicJobConfigSlaveDao.listConfig());
        DynamicDataSourceContextHolder.clearDataSourceType();

        System.out.println("********************************");
        System.out.println(dynamicJobConfigDao.listConfig());

        //System.out.println(jobConfigSlaveMapper.listConfig());
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("user thread executed");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("user thread end");
            }
        });
        System.out.println("启动用户线程");
        thread.start();
        System.out.println("用户线程调用join");
        thread.join();
        System.out.println("主线程执行结束");
    }

}
