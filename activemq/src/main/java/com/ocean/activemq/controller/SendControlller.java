package com.ocean.activemq.controller;

import com.ocean.activemq.config.ActiveMqName;
import com.ocean.activemq.producer.Sender;
import com.ocean.activemq.producer.queue.QueueSender;
import com.ocean.activemq.producer.topic.TopicSender;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Destination;

@RestController
@RequestMapping
@ResponseBody
public class SendControlller {

    @Autowired
    private Sender sender;

    @Autowired
    private QueueSender queueSender;

    @Autowired
    private TopicSender topicSender;

    @GetMapping(value = "/add/queue")
    public void addQueue() {
        Destination destination = new ActiveMQQueue(ActiveMqName.QUEUE_ONE);
        sender.sendTemple(destination, "success");
    }

    @GetMapping(value = "/add/topic")
    public void addTopic() {
        Destination destination = new ActiveMQTopic(ActiveMqName.TOPIC_ONE);
        sender.sendTemple(destination, "success");
    }

    @GetMapping(value = "/creat/queue")
    public void creatQueue() {
        queueSender.send(ActiveMqName.QUEUE_ONE, "success");
    }

    @GetMapping(value = "/creat/topic")
    public void creatTopic() {
        topicSender.send(ActiveMqName.TOPIC_ONE, "success");
    }
}
