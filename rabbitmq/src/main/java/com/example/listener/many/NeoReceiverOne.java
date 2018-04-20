package com.example.listener.many;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author guodahai
 */
@Component
@RabbitListener(queues = "neo")
public class NeoReceiverOne {

    private final Logger logger = LoggerFactory.getLogger(NeoReceiverOne.class);

    @RabbitHandler
    public void process(String neo) {
        logger.warn("ReceiverOne: " + neo);
    }

}
