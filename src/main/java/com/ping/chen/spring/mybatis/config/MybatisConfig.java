package com.ping.chen.spring.mybatis.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.ping.chen.spring.mybatis.dynamic.DataSourceEnum;
import com.ping.chen.spring.mybatis.dynamic.DynamicDataSourceResolver;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@MapperScan(basePackages = MybatisConfig.PACKAGE, sqlSessionFactoryRef = "dynamicSqlSessionFactory")
public class MybatisConfig {

    static final String PACKAGE = "com.ping.chen.spring.mybatis.dao";
    static final String MAPPER_LOCATION = "classpath:mapper/*.xml";

    @Value("${master.datasource.url}")
    private String masterUrl;

    @Value("${master.datasource.username}")
    private String masterUser;

    @Value("${master.datasource.password}")
    private String masterPassword;

    @Value("${master.datasource.driverClassName}")
    private String masterDriverClass;

    @Bean(name = "masterDataSource")
    @Primary
    public DataSource masterDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(masterDriverClass);
        dataSource.setUrl(masterUrl);
        dataSource.setUsername(masterUser);
        dataSource.setPassword(masterPassword);
        return dataSource;
    }

    @Value("${slave.datasource.url}")
    private String slaveUrl;

    @Value("${slave.datasource.username}")
    private String slaveUser;

    @Value("${slave.datasource.password}")
    private String slavePassword;

    @Value("${slave.datasource.driverClassName}")
    private String slaveDriverClass;

    @Bean(name = "slaveDataSource")
    public DataSource slaveDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(slaveDriverClass);
        dataSource.setUrl(slaveUrl);
        dataSource.setUsername(slaveUser);
        dataSource.setPassword(slavePassword);
        return dataSource;
    }

    @Bean(name = "dynamicDataSource")
    public DynamicDataSourceResolver dynamicDataSource(@Qualifier("masterDataSource") DataSource masterDataSource,
                                                       @Qualifier("slaveDataSource") DataSource slaveDataSource){
        DynamicDataSourceResolver resolver = new DynamicDataSourceResolver();
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DataSourceEnum.MASTER.getDataSource(), masterDataSource);
        dataSourceMap.put(DataSourceEnum.SLAVE.getDataSource(), slaveDataSource);

        resolver.setTargetDataSources(dataSourceMap);

        //设置默认数据源
        resolver.setDefaultTargetDataSource(masterDataSource);
        resolver.afterPropertiesSet();
        return resolver;
    }

    @Bean(name = "dynamicDataSourceTransactionManager")
    @Primary
    public DataSourceTransactionManager dynamicDataSourceTransactionManager(@Qualifier("dynamicDataSource") DataSource dynamicDataSource){
        return new DataSourceTransactionManager(dynamicDataSource);
    }

    @Bean(name = "dynamicSqlSessionFactory")
    @Primary
    public SqlSessionFactory dynamicSqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dynamicDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dynamicDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
        sessionFactory.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return sessionFactory.getObject();
    }

}
