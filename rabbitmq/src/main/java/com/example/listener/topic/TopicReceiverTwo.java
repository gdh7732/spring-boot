package com.example.listener.topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author guodahai
 */
@Component
@RabbitListener(queues = "topic.messages")
public class TopicReceiverTwo {

    private final Logger logger = LoggerFactory.getLogger(TopicReceiverTwo.class);

    @RabbitHandler
    public void process(String message) {
        logger.warn("Topic ReceiverTwo  : " + message);
    }

}
