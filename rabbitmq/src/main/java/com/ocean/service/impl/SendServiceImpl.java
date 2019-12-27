package com.ocean.service.impl;

import com.ocean.common.ErrorCodeEnum;
import com.ocean.common.ServiceException;
import com.ocean.model.Message;
import com.ocean.service.SendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author guodahai
 * @version 2018/4/20 下午3:41
 */
@Service
public class SendServiceImpl implements SendService {

    private final Logger logger = LoggerFactory.getLogger(SendServiceImpl.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Override
    public Boolean send(Message request) throws ServiceException {
        String exchange = request.getExchange();
        String routingKey = request.getRouteKey();
        Object object = request.getMsg();
        try {
            if (null == exchange) {
                send(routingKey, object);
            } else {
                send(exchange, routingKey, object);
            }
        } catch (ServiceException e) {
            logger.warn("发送消息失败！");
            throw new ServiceException(ErrorCodeEnum.P99);
        }
        return true;
    }

    @Override
    public void send(String routingKey, Object object) throws ServiceException {
        rabbitTemplate.convertAndSend(routingKey, object);
    }

    @Override
    public void send(String exchange, String routingKey, Object object) throws ServiceException {
        rabbitTemplate.convertAndSend(exchange, routingKey, object);
    }
}
