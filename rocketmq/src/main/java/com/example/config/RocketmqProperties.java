package com.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author guodahai
 * @version 2018/5/24 下午4:41
 */
@ConfigurationProperties(RocketmqProperties.PREFIX)
public class RocketmqProperties {

    public static final String PREFIX = "zebra.rocketmq";

    private String namesrvAddr;

    private String instanceName;

    private String clientIP;

    private ProducerConfig producer;

    private ConsumerConfig consumer;

    public String getNamesrvAddr() {
        return namesrvAddr;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public String getClientIP() {
        return clientIP;
    }

    public void setClientIP(String clientIP) {
        this.clientIP = clientIP;
    }

    public ProducerConfig getProducer() {
        return producer;
    }

    public void setProducer(ProducerConfig producer) {
        this.producer = producer;
    }

    public ConsumerConfig getConsumer() {
        return consumer;
    }

    public void setConsumer(ConsumerConfig consumer) {
        this.consumer = consumer;
    }
}
