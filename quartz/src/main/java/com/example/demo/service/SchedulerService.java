package com.example.demo.service;

import com.example.demo.entity.TriggerRequest;

/**
 * @author guodahai
 * @version 2018/4/16 下午5:25
 */
public interface SchedulerService {
    /**
     * 添加job任务
     *
     * @param request
     * @throws Exception
     */
    void add(TriggerRequest request) throws Exception;

    /**
     * 停止job任务
     *
     * @param request
     * @throws Exception
     */
    void pause(TriggerRequest request) throws Exception;

    /**
     * 复原job任务
     *
     * @param request
     * @throws Exception
     */
    void resume(TriggerRequest request) throws Exception;

    /**
     * 重启job任务
     *
     * @param request
     * @throws Exception
     */
    void reschedule(TriggerRequest request) throws Exception;

    /**
     * 删除job任务
     *
     * @param request
     * @throws Exception
     */
    void delete(TriggerRequest request) throws Exception;
}
