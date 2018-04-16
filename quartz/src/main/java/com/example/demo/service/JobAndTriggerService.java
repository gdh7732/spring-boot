package com.example.demo.service;


import com.example.demo.entity.JobAndTrigger;
import com.github.pagehelper.PageInfo;

/**
 * @author guodahai
 */
public interface JobAndTriggerService {
    /**
     * 分页查询定时任务
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<JobAndTrigger> getJobAndTriggerDetails(int pageNum, int pageSize);

}
