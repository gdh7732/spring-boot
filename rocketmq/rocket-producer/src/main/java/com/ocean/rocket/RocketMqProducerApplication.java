package com.ocean.rocket;

import com.ocean.rocket.annotation.EnableRocketMQConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author guodahai
 */
@EnableRocketMQConfig
@SpringBootApplication
public class RocketMqProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RocketMqProducerApplication.class, args);
    }
}
