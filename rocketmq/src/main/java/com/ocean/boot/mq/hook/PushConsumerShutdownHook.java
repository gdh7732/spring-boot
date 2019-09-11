package com.ocean.boot.mq.hook;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;

/**
 * DefaultMQPushConsumer关闭hook
 *
 * @author 003238
 */
@Slf4j
public class PushConsumerShutdownHook extends Thread {

    private DefaultMQPushConsumer consumer;

    public PushConsumerShutdownHook(DefaultMQPushConsumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public void run() {
        if (consumer != null) {
            log.info("DefaultMQPushConsumer shutdownHook:{}", consumer.getConsumerGroup());
            consumer.shutdown();
        }
    }
}
