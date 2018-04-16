package com.example.demo.service.impl;

import com.example.demo.dao.JobAndTriggerRepository;
import com.example.demo.entity.JobAndTrigger;
import com.example.demo.entity.TriggerRequest;
import com.example.demo.service.JobAndTriggerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author guodahai
 */
@Service
public class JobAndTriggerImpl implements JobAndTriggerService {

    @Autowired
    private JobAndTriggerRepository jobAndTriggerRepository;

    @Override
    public PageInfo<JobAndTrigger> getJobAndTriggerDetails(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<JobAndTrigger> list = jobAndTriggerRepository.findAll();
        PageInfo<JobAndTrigger> page = new PageInfo<>(list);
        return page;
    }

    @Override
    public List<JobAndTrigger> getAll() throws Exception {
        return jobAndTriggerRepository.findAll();
    }

    @Override
    public Boolean create(TriggerRequest request) throws Exception {
        JobAndTrigger jobAndTrigger = new JobAndTrigger();
        jobAndTrigger.setJobClassName(request.getJobClassName());
        jobAndTrigger.setJobGroup(request.getJobGroup());
        jobAndTrigger.setCronExpression(request.getCronExpression());
        jobAndTriggerRepository.save(jobAndTrigger);
        return true;
    }

}