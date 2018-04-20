package com.example.listener.topic;

import com.example.listener.object.ObjectReceiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author guodahai
 */
@Component
@RabbitListener(queues = "topic.message")
public class TopicReceiverOne {

    private final Logger logger = LoggerFactory.getLogger(TopicReceiverOne.class);

    @RabbitHandler
    public void process(String message) {
        logger.warn("Topic ReceiverOne  : " + message);
    }

}
