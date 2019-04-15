package com.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 路由模式
 *
 * @author guodahai
 * @version 2018/5/18 下午4:51
 */
@Configuration
public class RouteRabbitConfig {

    @Bean
    public Queue routeA() {
        return new Queue("route.A");
    }

    @Bean
    public Queue routeB() {
        return new Queue("route.B");
    }

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange("routing");
    }

    @Bean
    Binding bindingRouteA(Queue routeA, DirectExchange directExchange) {
        return BindingBuilder.bind(routeA).to(directExchange).with("info");
    }

    @Bean
    Binding bindingRouteB(Queue routeB, DirectExchange directExchange) {
        return BindingBuilder.bind(routeB).to(directExchange).with("error");
    }

}
