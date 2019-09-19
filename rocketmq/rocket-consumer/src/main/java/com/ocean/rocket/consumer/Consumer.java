package com.ocean.rocket.consumer;

import com.ocean.rocket.annotation.RocketMQConsumer;
import com.ocean.rocket.base.AbstractMQPushConsumer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * 生产者
 *
 * @author ocean
 */
@Slf4j
@RocketMQConsumer(consumerGroup = "ocean", topic = "topic")
public class Consumer extends AbstractMQPushConsumer<String> {

    @Override
    public boolean processMessage(String message, MessageExt messageExt) {
        log.warn("Consumer========");
        return true;
    }
}