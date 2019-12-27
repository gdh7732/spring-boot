package com.ocean.activemq.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

@Service
public class Sender {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendTemple(Destination destination, final String message) {
        jmsMessagingTemplate.convertAndSend(destination, message);
    }
}
