package com.example.listener.fanout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author guodahai
 */
@Component
public class FanoutReceiver {

    private final Logger logger = LoggerFactory.getLogger(FanoutReceiver.class);

    @RabbitHandler
    @RabbitListener(queues = "fanout.A")
    public void processA(String message) {
        logger.warn("fanout Receiver A: " + message);
    }


    @RabbitHandler
    @RabbitListener(queues = "fanout.B")
    public void processB(String message) {
        logger.warn("fanout Receiver B: " + message);
    }


    @RabbitHandler
    @RabbitListener(queues = "fanout.C")
    public void processC(String message) {
        logger.warn("fanout Receiver C: " + message);
    }

}
