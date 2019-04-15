package com.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Exchange四种模式之一：topic
 * 提前绑定Exchange,RouteKey
 *
 * @author guodahai
 */
@Configuration
public class TopicRabbitConfig {

    final static String MESSAGE_ONE = "message.one";
    final static String MESSAGE_TWO = "message.two";
    final static String INFO_ONE = "info.one";
    final static String INFO_TWO = "info.two";

    @Bean
    public Queue queueMessageOne() {
        return new Queue(TopicRabbitConfig.MESSAGE_ONE);
    }

    @Bean
    public Queue queueMessageTwo() {
        return new Queue(TopicRabbitConfig.MESSAGE_TWO);
    }

    @Bean
    public Queue queueInfoOne() {
        return new Queue(TopicRabbitConfig.INFO_ONE);
    }

    @Bean
    public Queue queueInfoTwo() {
        return new Queue(TopicRabbitConfig.INFO_TWO);
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    @Bean
    TopicExchange oneExchange() {
        return new TopicExchange("oneExchange");
    }

    @Bean
    Binding bindingTopicExchangeOne(Queue queueMessageOne, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueMessageOne).to(topicExchange).with("topic.*");
    }

    @Bean
    Binding bindingTopicExchangeTwo(Queue queueMessageTwo, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueMessageTwo).to(topicExchange).with("topic.#");
    }

    @Bean
    Binding bindingOneExchangeMessage(Queue queueMessageOne, TopicExchange oneExchange) {
        return BindingBuilder.bind(queueMessageOne).to(oneExchange).with("*.one");
    }

    @Bean
    Binding bindingOneExchangeInfo(Queue queueInfoOne, TopicExchange oneExchange) {
        return BindingBuilder.bind(queueInfoOne).to(oneExchange).with("#.one");
    }

}
