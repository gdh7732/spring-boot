package com.ocean.boot.mq.annotation;

import java.lang.annotation.*;

/**
 * RocketMQ启用注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface EnableRocketMQConfig {

}
