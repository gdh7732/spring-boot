package com.ocean.boot.mq.config;

import com.ocean.boot.mq.annotation.EnableRocketMQConfig;
import com.ocean.boot.mq.constants.MessageConstant;
import com.ocean.boot.mq.exception.RocketMqException;
import com.ocean.boot.mq.hook.ProducerShutdownHook;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.StandardEnvironment;

import javax.annotation.PostConstruct;

/**
 * 自动装配producer
 *
 * @author ocean
 */
@Slf4j
@ConditionalOnBean(annotation = EnableRocketMQConfig.class)
@ConditionalOnClass(DefaultMQProducer.class)
@EnableConfigurationProperties({ProducerProperties.class})
@Configuration
public class ProducerAutoConfig implements ApplicationContextAware {
    protected ApplicationContext applicationContext;
    @Autowired
    protected StandardEnvironment standardEnvironment;
    @Autowired
    protected ProducerProperties producerProperties;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Bean
    public DefaultMQProducer defaultMQProducer(ProducerProperties properties) throws MQClientException {
        if (StringUtils.isEmpty(producerProperties.getProducerGroup())) {
            throw new RocketMqException("producerGroup must be defined");
        }
        if (StringUtils.isEmpty(producerProperties.getNamesrvAddr())) {
            throw new RocketMqException("producer namesrvAddr must be defined");
        }
        DefaultMQProducer producer = new DefaultMQProducer(producerProperties.getProducerGroup());
        this.configure(producer);
        producer.start();
        log.info("DefaultMQProducer started:{}", producer.getProducerGroup());
        // 应用退出时，调用shutdown来清理资源，关闭网络连接，从RocketMQ服务器上注销
        Runtime.getRuntime().addShutdownHook(new ProducerShutdownHook(producer));
        return producer;
    }

    /**
     * producer配置
     *
     * @param producer
     */
    public void configure(DefaultMQProducer producer) {
        producer.setNamesrvAddr(producerProperties.getNamesrvAddr());
        producer.setProducerGroup(producerProperties.getProducerGroup());
        producer.setInstanceName(producerProperties.getInstanceName());
        producer.setDefaultTopicQueueNums(producerProperties.getDefaultTopicQueueNums());
        producer.setMaxMessageSize(producerProperties.getMaxMessageSize());
        producer.setRetryAnotherBrokerWhenNotStoreOK(producerProperties.isRetryAnotherBrokerWhenNotStoreOK());
        producer.setRetryTimesWhenSendAsyncFailed(producerProperties.getRetryTimesWhenSendAsyncFailed());
        producer.setRetryTimesWhenSendFailed(producerProperties.getRetryTimesWhenSendFailed());
        producer.setSendMsgTimeout(producerProperties.getSendMsgTimeout());
        producer.setUnitMode(producerProperties.isUnitMode());
        producer.setUnitName(producerProperties.getUnitName());
        producer.setVipChannelEnabled(producerProperties.isVipChannelEnabled());
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