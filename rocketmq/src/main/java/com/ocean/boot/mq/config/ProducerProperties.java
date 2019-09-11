package com.ocean.boot.mq.config;

import lombok.Data;
import org.apache.rocketmq.client.ClientConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * RocketMQ的producer配置参数
 *
 * @author ocean
 */
@Data
@ConfigurationProperties(prefix = ProducerProperties.PREFIX)
public class ProducerProperties extends ClientConfig {
    public static final String PREFIX = "rocketmq.producer";
    /**
     * namesrv地址
     */
    private String namesrvAddr;
    /**
     * 生产者Group名称
     */
    private String producerGroup;
    /**
     * 是否开启VIP通道
     */
    private boolean vipChannelEnabled = false;

    /**
     * Number of queues to create per default topic.
     */
    private volatile int defaultTopicQueueNums = 4;

    /**
     * Timeout for sending messages.
     */
    private int sendMsgTimeout = 3000;

    /**
     * Maximum number of retry to perform internally before claiming sending
     * failure in synchronous mode.
     * This may potentially cause message duplication which is up to application
     * developers to resolve.
     */
    private int retryTimesWhenSendFailed = 3;

    /**
     * Maximum number of retry to perform internally before claiming sending
     * failure in asynchronous mode.
     * </p>
     * <p>
     * This may potentially cause message duplication which is up to application
     * developers to resolve.
     */
    private int retryTimesWhenSendAsyncFailed = 3;

    /**
     * Indicate whether to retry another broker on sending failure internally.
     */
    private boolean retryAnotherBrokerWhenNotStoreOK = false;

    /**
     * Maximum allowed message size in bytes.
     */
    private int maxMessageSize = 1024 * 1024 * 4; // 4M

    /**
     * 是否启用事务
     */
    private boolean transaction = false;
}
