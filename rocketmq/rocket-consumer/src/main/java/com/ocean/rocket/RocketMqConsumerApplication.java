package com.ocean.rocket;

import com.ocean.rocket.annotation.EnableRocketMQConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author guodahai
 */
@EnableRocketMQConfig
@SpringBootApplication
public class RocketMqConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RocketMqConsumerApplication.class, args);
    }
}
