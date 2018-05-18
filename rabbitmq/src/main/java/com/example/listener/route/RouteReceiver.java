package com.example.listener.route;

import com.example.listener.fanout.FanoutReceiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author guodahai
 * @version 2018/5/18 下午5:04
 */
@Component
public class RouteReceiver {

    private final Logger logger = LoggerFactory.getLogger(FanoutReceiver.class);

    @RabbitHandler
    @RabbitListener(queues = "route.A")
    public void processA(String message) {
        logger.warn("route Receiver A: " + message);
    }


    @RabbitHandler
    @RabbitListener(queues = "route.B")
    public void processB(String message) {
        logger.warn("route Receiver B: " + message);
    }

}
