package com.example.rabbit.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author guodahai
 */
@Component
@RabbitListener(queues = "topic.messages")
public class TopicReceiverTwo {

    @RabbitHandler
    public void process(String message) {
        System.out.println("Topic ReceiverTwo  : " + message);
    }

}
