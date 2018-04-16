package com.example.demo.service;


import com.example.demo.entity.JobAndTrigger;
import com.example.demo.entity.TriggerRequest;
import com.github.pagehelper.PageInfo;

import java.util.List;

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
    PageInfo<JobAndTrigger> getJobAndTriggerDetails(int pageNum, int pageSize) throws Exception;

    /**
     * 查询所有定时任务
     *
     * @return
     * @throws Exception
     */
    List<JobAndTrigger> getAll() throws Exception;

    /**
     * 添加定时任务
     *
     * @param request
     * @return
     * @throws Exception
     */
    Boolean create(TriggerRequest request) throws Exception;

}
