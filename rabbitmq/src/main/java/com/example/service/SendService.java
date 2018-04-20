package com.example.service;

import com.example.common.ServiceException;
import com.example.model.SendRequest;

/**
 * @author guodahai
 * @version 2018/4/20 下午3:40
 */
public interface SendService {

    /**
     * 消息发送
     *
     * @param request
     * @return
     */
    Boolean send(SendRequest request) throws ServiceException;

    /**
     * 简单模式、工作队列模式
     *
     * @param routingKey
     * @param object
     */
    void send(String routingKey, Object object) throws ServiceException;

    /**
     * Exchange模式
     *
     * @param exchange
     * @param routingKey
     * @param object
     */
    void send(String exchange, String routingKey, Object object) throws ServiceException;
}
