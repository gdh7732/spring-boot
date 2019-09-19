package com.ocean.rocket.producer;

import com.ocean.rocket.annotation.RocketMQProducer;
import com.ocean.rocket.base.AbstractRocketMQProducer;
import org.springframework.stereotype.Component;

/**
 * 生产者
 *
 * @author ocean
 */
@Component
@RocketMQProducer
public class Producer extends AbstractRocketMQProducer {

}