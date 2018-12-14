package com.bluepay.spring.mybatis.demo;

import com.bluepay.spring.mybatis.dao.master.TsTransactionDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {
    public static void main(String[] args) throws IOException {
        mybatisTest();
    }

    public static void mybatisTest() throws IOException {
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            TsTransactionDao transactionDao = sqlSession.getMapper(TsTransactionDao.class);
            List<TsTransaction> jobConfigList = transactionDao.listAll(TransactionType.CASH);
            System.out.println(jobConfigList);
        }  finally {
            if (sqlSession != null)
                sqlSession.close();
        }
    }
}
