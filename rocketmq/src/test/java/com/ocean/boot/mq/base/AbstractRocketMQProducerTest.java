package com.ocean.boot.mq.base;

import com.ocean.boot.mq.RocketMqApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RocketMqApplication.class)
public class AbstractRocketMQProducerTest {

    @Resource
    private AbstractRocketMQProducer abstractRocketMQProducer;

    @Test
    public void sendOneWay() {
        abstractRocketMQProducer.sendOneWay("a", "a", "message");
    }

    @Test
    public void sendOneWayOrderly() {
    }

    @Test
    public void syncSend() {
    }

    @Test
    public void syncSendBatch() {
    }

    @Test
    public void syncSendOrderly() {
    }

    @Test
    public void asyncSend() {
    }

    @Test
    public void asyncSend1() {
    }

    @Test
    public void syncSendDelay() {
    }

    @Test
    public void doAfterSyncSend() {
    }
}