package com.example.rabbit.many;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author guodahai
 */
@Component
public class NeoSenderTwo {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(int i) {
        String context = "spring boot neo queue" + " ****** " + i;
        System.out.println("SenderTwo : " + context);
        this.rabbitTemplate.convertAndSend("neo", context);
    }

}