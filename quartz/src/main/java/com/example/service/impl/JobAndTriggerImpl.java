package com.example.service.impl;

import com.example.common.ErrorCodeEnum;
import com.example.common.ServiceException;
import com.example.dao.JobAndTriggerRepository;
import com.example.entity.JobAndTrigger;
import com.example.entity.TriggerRequest;
import com.example.service.JobAndTriggerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 * @author guodahai
 */
@Service
public class JobAndTriggerImpl implements JobAndTriggerService {

    private Logger logger = LoggerFactory.getLogger(JobAndTrigger.class);

    @Autowired
    private JobAndTriggerRepository repository;

    @Override
    public Map getJobAndTriggerDetails(int pageNum, int pageSize) throws ServiceException {
        PageHelper.startPage(pageNum, pageSize);
        List<JobAndTrigger> list = repository.findAll();
        PageInfo<JobAndTrigger> page = new PageInfo<>(list);
        Map<String, Object> map = new HashMap<>();
        map.put("JobAndTrigger", page);
        map.put("number", page.getTotal());
        return map;
    }

    @Override
    public List<JobAndTrigger> getAll() throws ServiceException {
        return repository.findAll();
    }

    @Override
    public Boolean create(TriggerRequest request) throws ServiceException {
        JobAndTrigger jobAndTrigger = new JobAndTrigger();
        jobAndTrigger.setJobClassName(request.getJobClassName());
        jobAndTrigger.setJobGroup(request.getJobGroup());
        jobAndTrigger.setCronExpression(request.getCronExpression());
        try {
            repository.save(jobAndTrigger);
        } catch (Exception e) {
            logger.error("插入数据失败");
            throw new ServiceException(ErrorCodeEnum.I01);
        }
        return true;
    }

    @Override
    public JobAndTrigger findOne(TriggerRequest request) throws ServiceException {
        Example<JobAndTrigger> example = new Example<JobAndTrigger>() {
            @Override
            public JobAndTrigger getProbe() {
                JobAndTrigger trigger = new JobAndTrigger();
                BeanUtils.copyProperties(request, trigger);
                return trigger;
            }

            @Override
            public ExampleMatcher getMatcher() {
                //创建匹配器，即如何使用查询条件
                return ExampleMatcher.matching()
                        .withMatcher("jobClassName", ExampleMatcher.GenericPropertyMatchers.startsWith())
                        .withIgnorePaths("cronExpression");

            }
        };
        Optional<JobAndTrigger> result = repository.findOne(example);
        return result.get();
    }

    @Override
    public Boolean update(JobAndTrigger trigger) throws ServiceException {
        try {
            repository.save(trigger);
        } catch (Exception e) {
            logger.error("更新数据失败");
            throw new ServiceException(ErrorCodeEnum.U01);
        }
        return true;
    }

}