package com.ocean.activemq.consumer.topic;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class TopicConsumer {

    @JmsListener(destination = "topic1", containerFactory = "jmsListenerContainerTopic")
    public void getTopic1(String info) {
        System.out.println("topicListener1成功监听topic1消息队列，传来的值为:" + info);
    }

    @JmsListener(destination = "topic1", containerFactory = "jmsListenerContainerTopic")
    public void getTopic2(String info) {
        System.out.println("topicListener2成功监听topic1消息队列，传来的值为:" + info);
    }

}
