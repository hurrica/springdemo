package com.bluepay.spring.mybatis.service;

import com.bluepay.spring.model.JobConfig;
import com.bluepay.spring.mybatis.dao.BaseDao;
import com.bluepay.spring.mybatis.dao.master.JobConfigDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobConfigService extends ServiceImpl<JobConfig, BaseDao<JobConfig>> {
    @Autowired
    private JobConfigDao jobConfigMapper;

    public List listConfig(){
        return jobConfigMapper.listConfig();
    }
}
