package com.ocean.boot.mq.spring;

import com.ocean.boot.mq.annotation.EnableRocketMQConfig;
import com.ocean.boot.mq.base.AbstractRocketMQProducer;
import org.springframework.stereotype.Component;

/**
 * 生产者
 *
 * @author ocean
 */
@Component
@EnableRocketMQConfig
public class Producer extends AbstractRocketMQProducer {


}