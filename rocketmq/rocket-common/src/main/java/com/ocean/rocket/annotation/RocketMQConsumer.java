package com.ocean.rocket.annotation;

import com.ocean.rocket.enums.ConsumeMode;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * RocketMQ消费者自动装配注解
 *
 * @author ocean
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface RocketMQConsumer {
    /**
     * 消费group名称
     */
    String consumerGroup();

    /**
     * 消息topic
     */
    String topic();

    /**
     * 消息标签,用于区分一类消息
     */
    String[] tag() default {"*"};

    /**
     * 广播模式消费： BROADCASTING
     * 集群模式消费(默认)： CLUSTERING
     */
    MessageModel messageMode() default MessageModel.CLUSTERING;

    /**
     * 并发消费(默认): CONCURRENTLY
     * 顺序消费: ORDERLY
     */
    ConsumeMode consumeMode() default ConsumeMode.CONCURRENTLY;
}
