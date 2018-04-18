package com.example.rabbit.many;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author guodahai
 */
@Component
@RabbitListener(queues = "neo")
public class NeoReceiverOne {

    @RabbitHandler
    public void process(String neo) {
        System.out.println("ReceiverOne: " + neo);
    }

}
