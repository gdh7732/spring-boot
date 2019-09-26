package com.ocean.rocket.producer;

import com.ocean.rocket.annotation.RocketMQProducer;
import com.ocean.rocket.base.AbstractRocketMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.stereotype.Component;

/**
 * 生产者
 *
 * @author ocean
 */
@Component
@RocketMQProducer
public class Producer extends AbstractRocketMQProducer {

    /**
     * 重写此方法处理发送后的逻辑
     *
     * @param sendResult 发送结果
     */
    @Override
    public void doAfterSyncSend(SendResult sendResult) {
    }

}