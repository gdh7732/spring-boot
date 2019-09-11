package com.ocean.boot.mq.config;

import lombok.Data;
import org.apache.rocketmq.client.ClientConfig;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * RocketMQ的consumer配置参数
 *
 * @author ocean
 */
@Data
@ConfigurationProperties(prefix = ConsumerProperties.PREFIX)
public class ConsumerProperties extends ClientConfig {
    public static final String PREFIX = "rocketmq.consumer";
    /**
     * 是否开启VIP通道
     */
    private boolean vipChannelEnabled = false;

    /**
     * Max re-consume times. -1 means 16 times.
     * If messages are re-consumed more than {@link #maxReconsumeTimes} before success, it's be directed to a deletion
     * queue waiting.
     */
    private int maxReconsumeTimes = -1;

    /**
     * Maximum number of retry to perform internally before claiming consume failure.
     */
    private int retryTimesWhenConsumeFailed = 16;

    /**
     * Maximum amount of time in minutes a message may block the consuming thread.
     */
    private long consumeTimeout = 15;

    /**
     * Consuming point on consumer booting.
     */
    private String consumeFromWhere = ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET.toString();
}
