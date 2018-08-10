package com.bluepay.spring.mybatis.demo;

import com.bluepay.spring.model.JobConfig;
import com.bluepay.spring.mybatis.dao.master.JobConfigDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MybatisTest {
    public static void main(String[] args) throws IOException {
        AtomicInteger atomicInteger = new AtomicInteger(10);
        String ss = new String("test");
        String ss1 = ss;
        ss = ss.replaceAll("t", "*");
        System.out.println("初始值：" + ss1 + "  新值：" + ss);
    }

    public static void mybatisTest() throws IOException {
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            JobConfigDao jobConfigDao = sqlSession.getMapper(JobConfigDao.class);
            List<JobConfig> jobConfigList = jobConfigDao.listConfig();
            System.out.println(jobConfigList);
        } finally {
            if (sqlSession != null)
                sqlSession.close();
        }
    }
}
