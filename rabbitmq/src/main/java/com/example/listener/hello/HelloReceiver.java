package com.example.listener.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author guodahai
 */
@Component
@RabbitListener(queues = "hello")
public class HelloReceiver {

    private final Logger logger = LoggerFactory.getLogger(HelloReceiver.class);

    @RabbitHandler
    public void process(String message) {
        logger.warn("Receiver  : " + message);
    }

}
