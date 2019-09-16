package com.ocean.boot.mq.annotation;

import java.lang.annotation.*;

/**
 * 用来标识作为消息key的字段，尽可能全局唯一
 * prefix 会作为前缀拼到字段值前面
 *
 * @author ocean
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RocketMQKey {
    String prefix() default "";
}
