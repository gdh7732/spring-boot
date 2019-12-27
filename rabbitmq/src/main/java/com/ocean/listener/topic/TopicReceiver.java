package com.ocean.listener.topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author guodahai
 */
@Component

public class TopicReceiver {

    private final Logger logger = LoggerFactory.getLogger(TopicReceiver.class);

    @RabbitHandler
    @RabbitListener(queues = "message.one")
    public void processA(String message) {
        logger.warn("message.one receiver  : " + message);
    }

    @RabbitHandler
    @RabbitListener(queues = "message.two")
    public void processB(String message) {
        logger.warn("message.two receiver  : " + message);
    }

    @RabbitHandler
    @RabbitListener(queues = "info.one")
    public void processC(String message) {
        logger.warn("info.one receiver  : " + message);
    }

    @RabbitHandler
    @RabbitListener(queues = "info.two")
    public void processD(String message) {
        logger.warn("info.two receiver  : " + message);
    }

}
