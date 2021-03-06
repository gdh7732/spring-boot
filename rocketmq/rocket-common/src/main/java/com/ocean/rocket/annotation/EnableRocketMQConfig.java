package com.ocean.rocket.annotation;

import java.lang.annotation.*;

/**
 * RocketMQ启用注解
 *
 * @author ocean
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface EnableRocketMQConfig {

}
