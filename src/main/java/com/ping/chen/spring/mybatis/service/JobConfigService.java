package com.ping.chen.spring.mybatis.service;

import com.ping.chen.spring.model.JobConfig;
import com.ping.chen.spring.mybatis.dao.BaseDao;
import com.ping.chen.spring.mybatis.dao.master.JobConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobConfigService extends ServiceImpl<JobConfig, BaseDao<JobConfig>> {
    @Autowired
    private JobConfigMapper jobConfigMapper;

    public List listConfig(){
        return jobConfigMapper.listConfig();
    }
}
