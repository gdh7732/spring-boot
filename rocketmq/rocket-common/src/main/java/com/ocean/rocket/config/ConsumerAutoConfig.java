package com.ocean.rocket.config;

import com.ocean.rocket.annotation.EnableRocketMQConfig;
import com.ocean.rocket.annotation.RocketMQConsumer;
import com.ocean.rocket.base.AbstractMQPushConsumer;
import com.ocean.rocket.enums.ConsumeMode;
import com.ocean.rocket.exception.RocketMqException;
import com.ocean.rocket.hook.PushConsumerShutdownHook;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * 自动装配消息consumer
 *
 * @author ocean
 */
@Slf4j
@ConditionalOnBean(annotation = EnableRocketMQConfig.class)
@EnableConfigurationProperties({ConsumerProperties.class})
@Configuration
public class ConsumerAutoConfig extends BaseAutoConfig {
    @Autowired
    private ConsumerProperties consumerProperties;

    @PostConstruct
    public void init() throws Exception {
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(RocketMQConsumer.class);
        // consumerGroup与topic关系
        Map<String, String> consumerGroupTopicMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : beans.entrySet()) {
            publishConsumer(entry.getKey(), entry.getValue(), consumerGroupTopicMap);
        }
    }

    private void publishConsumer(String beanName, Object bean, Map<String, String> consumerGroupTopicMap) throws RocketMqException, MQClientException {
        RocketMQConsumer mqConsumer = applicationContext.findAnnotationOnBean(beanName, RocketMQConsumer.class);
        if (StringUtils.isEmpty(consumerProperties.getNamesrvAddr())) {
            throw new RocketMqException("consumer namesrvAddr must be defined");
        }
        if (StringUtils.isEmpty(mqConsumer.consumerGroup())) {
            throw new RocketMqException("consumerGroup must be defined");
        }
        if (StringUtils.isEmpty(mqConsumer.topic())) {
            throw new RocketMqException("consumer topic must be defined");
        }
        if (!AbstractMQPushConsumer.class.isAssignableFrom(bean.getClass())) {
            throw new RocketMqException(bean.getClass().getName() + "-consumer未继承AbstractMQPushConsumer");
        }
        if (mqConsumer.consumeMode() == ConsumeMode.ORDERLY && mqConsumer.messageMode() == MessageModel.BROADCASTING) {
            throw new RocketMqException("Bad definition in @RocketMQConsumer, messageModel BROADCASTING does not support ORDERLY message");
        }
        // 消费group名称
        String consumerGroup = applicationContext.getEnvironment().getProperty(mqConsumer.consumerGroup());
        if (StringUtils.isEmpty(consumerGroup)) {
            consumerGroup = standardEnvironment.resolvePlaceholders(mqConsumer.consumerGroup());
        }
        // 消息topic
        String topic = applicationContext.getEnvironment().getProperty(mqConsumer.topic());
        if (StringUtils.isEmpty(topic)) {
            topic = standardEnvironment.resolvePlaceholders(mqConsumer.topic());
        }
        String checkTopic = consumerGroupTopicMap.get(consumerGroup);
        if (checkTopic != null && !checkTopic.equals(topic)) {
            throw new RocketMqException(consumerGroup + " consumerGroup already assigned topic " + checkTopic + " , choose another consumerGroup for topic " + topic);
        }
        // 消息tags
        String[] tags = mqConsumer.tag();
        Set<String> msgTags = new HashSet<>();
        if (tags != null && tags.length > 0) {
            for (int i = 0; i < tags.length; i++) {
                String tag = tags[i];
                String msgTag = applicationContext.getEnvironment().getProperty(tag);
                if (StringUtils.isEmpty(msgTag)) {
                    msgTag = standardEnvironment.resolvePlaceholders(tag);
                    msgTags.add(msgTag);
                }
            }
        }
        // 配置push consumer
        if (AbstractMQPushConsumer.class.isAssignableFrom(bean.getClass())) {
            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerGroup);
            this.configure(consumer);
            consumer.setMessageModel(mqConsumer.messageMode());
            consumer.subscribe(topic, StringUtils.join(msgTags, "||"));
            AbstractMQPushConsumer abstractMQPushConsumer = (AbstractMQPushConsumer) bean;
            if (mqConsumer.consumeMode() == ConsumeMode.CONCURRENTLY) {
                consumer.registerMessageListener(new MessageListenerConcurrently() {
                    @Override
                    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                                    ConsumeConcurrentlyContext context) {
                        return abstractMQPushConsumer.dealMessage(msgs, context);
                    }
                });
            } else if (mqConsumer.consumeMode() == ConsumeMode.ORDERLY) {
                consumer.registerMessageListener(
                        new MessageListenerOrderly() {
                            @Override
                            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs,
                                                                       ConsumeOrderlyContext context) {
                                return abstractMQPushConsumer.dealMessage(msgs, context);
                            }
                        });
            } else {
                throw new RocketMqException("Unknown consume mode!");
            }
            consumerGroupTopicMap.put(consumerGroup, topic);
            abstractMQPushConsumer.setConsumer(consumer);
            abstractMQPushConsumer.setConsumerProperties(consumerProperties);
            consumer.start();
            // 应用退出时，调用shutdown来清理资源，关闭网络连接，从RocketMQ服务器上注销
            Runtime.getRuntime().addShutdownHook(new PushConsumerShutdownHook(consumer));
        }
        log.info("{} is ready to subscribe message", bean.getClass().getName());
    }

    /**
     * consumer配置
     *
     * @param consumer
     */
    public void configure(DefaultMQPushConsumer consumer) {
        consumer.setNamesrvAddr(consumerProperties.getNamesrvAddr());
        consumer.setConsumeFromWhere(ConsumeFromWhere.valueOf(consumerProperties.getConsumeFromWhere()));
        consumer.setConsumeTimeout(consumerProperties.getConsumeTimeout());
        consumer.setInstanceName(consumerProperties.getInstanceName());
        consumer.setMaxReconsumeTimes(consumerProperties.getMaxReconsumeTimes());
        consumer.setUnitMode(consumerProperties.isUnitMode());
        consumer.setUnitName(consumerProperties.getUnitName());
        consumer.setVipChannelEnabled(consumerProperties.isVipChannelEnabled());
    }
}