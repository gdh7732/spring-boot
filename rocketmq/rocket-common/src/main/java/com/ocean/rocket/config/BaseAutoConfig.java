package com.ocean.rocket.config;

import com.ocean.rocket.annotation.EnableRocketMQConfig;
import com.ocean.rocket.base.AbstractMQPushConsumer;
import com.ocean.rocket.constants.MessageConstant;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.StandardEnvironment;

import javax.annotation.PostConstruct;

/**
 * RocketMQ基本配置
 *
 * @author ocean
 */
@Configuration
@ConditionalOnBean(annotation = EnableRocketMQConfig.class)
@AutoConfigureAfter(AbstractMQPushConsumer.class)
public class BaseAutoConfig implements ApplicationContextAware {
    @Autowired
    protected StandardEnvironment standardEnvironment;

    ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void setClientLoggerProperties() {
        setSystemProperty(MessageConstant.ROCKETMQ_CLIENT_LOG_LOADCONFIG);
        setSystemProperty(MessageConstant.ROCKETMQ_CLIENT_LOGROOT);
        setSystemProperty(MessageConstant.CLIENT_LOG_LEVEL);
    }

    private void setSystemProperty(String key) {
        if (standardEnvironment.containsProperty(key)) {
            System.setProperty(key, standardEnvironment.getProperty(key));
        }
    }
}

