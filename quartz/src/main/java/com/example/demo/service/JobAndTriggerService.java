package com.example.demo.service;


import com.example.demo.common.ServiceException;
import com.example.demo.entity.JobAndTrigger;
import com.example.demo.entity.TriggerRequest;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

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
    Map getJobAndTriggerDetails(int pageNum, int pageSize) throws ServiceException;

    /**
     * 查询所有定时任务
     *
     * @return
     * @throws ServiceException
     */
    List<JobAndTrigger> getAll() throws ServiceException;

    /**
     * 添加定时任务
     *
     * @param request
     * @return
     * @throws ServiceException
     */
    Boolean create(TriggerRequest request) throws ServiceException;

    /**
     * 查询定时任务
     *
     * @param request
     * @return
     * @throws ServiceException
     */
    JobAndTrigger findOne(TriggerRequest request) throws ServiceException;

    /**
     * 修改定时任务
     *
     * @param trigger
     * @return
     * @throws ServiceException
     */
    Boolean update(JobAndTrigger trigger) throws ServiceException;

}
