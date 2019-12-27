package com.ocean.listener.work;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author guodahai
 */
@Component
@RabbitListener(queues = "work")
public class WorkReceiverTwo {

    private final Logger logger = LoggerFactory.getLogger(WorkReceiverTwo.class);

    @RabbitHandler
    public void process(String message) {
        logger.warn("ReceiverTwo: " + message);
    }

}
