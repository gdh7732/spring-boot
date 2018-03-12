package com.example.activemq.consumer.queue;

import com.example.activemq.config.ActiveMqName;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class QueueConsumer {

    @JmsListener(destination =  ActiveMqName.QUEUE_ONE, containerFactory = "jmsListenerContainerQueue")
    public void getQueue1(String info) {
        System.out.println("listener1成功监听queue1消息队列，传来的值为:" + info);
    }

    @JmsListener(destination = ActiveMqName.QUEUE_ONE, containerFactory = "jmsListenerContainerQueue")
    public void getQueue2(String info) {
        System.out.println("listener2成功监听queue1消息队列，传来的值为:" + info);
    }

    @JmsListener(destination =  ActiveMqName.QUEUE_TWO, containerFactory = "jmsListenerContainerQueue")
    public void getQueue3(String info) {
        System.out.println("listener3成功监听queue1消息队列，传来的值为:" + info);
    }

}
