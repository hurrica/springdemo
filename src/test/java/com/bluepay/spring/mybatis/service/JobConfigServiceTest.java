package com.bluepay.spring.mybatis.service;


import com.bluepay.spring.Application;
import com.bluepay.spring.mybatis.dao.DynamicJobConfigDao;
import com.bluepay.spring.mybatis.dao.DynamicJobConfigSlaveDao;
import com.bluepay.spring.mybatis.dao.slave.JobConfigSlaveDao;
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
    JobConfigSlaveDao jobConfigSlaveDao;

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

        //System.out.println(jobConfigSlaveDao.listConfig());
    }

}
