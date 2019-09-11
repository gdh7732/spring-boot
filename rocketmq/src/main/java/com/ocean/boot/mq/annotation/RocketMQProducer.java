package com.ocean.boot.mq.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * RocketMQ生产者自动装配注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface RocketMQProducer {

}
