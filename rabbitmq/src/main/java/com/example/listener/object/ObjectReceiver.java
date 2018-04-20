package com.example.listener.object;

import com.example.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author guodahai
 */
@Component
@RabbitListener(queues = "object")
public class ObjectReceiver {

    private final Logger logger = LoggerFactory.getLogger(ObjectReceiver.class);

    @RabbitHandler
    public void process(User user) {
        logger.warn("Receiver object : " + user);
    }

}
