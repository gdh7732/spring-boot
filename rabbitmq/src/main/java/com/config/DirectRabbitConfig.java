package com.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Exchange四种模式之一：Direct
 * 无需绑定Exchange，需要指定RouteKey
 *
 * @author guodahai
 */
@Configuration
public class DirectRabbitConfig {

    /**
     * 简单模式 Hello Word
     * 一个生产者，一个队列，一个消费者
     *
     * @return
     */
    @Bean
    public Queue helloQueue() {
        return new Queue("hello");
    }

    /**
     * 工作队列模式Work Queue
     * 一个生产者，一个队列，多个消费者
     *
     * @return
     */
    @Bean
    public Queue workQueue() {
        return new Queue("work");
    }

    @Bean
    public Queue objectQueue() {
        return new Queue("object");
    }


}
