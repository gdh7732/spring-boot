package com.ocean.boot.mq.hook;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.MQProducer;

/**
 * MQProducer关闭hook
 *
 * @author 003238
 */
@Slf4j
public class ProducerShutdownHook extends Thread {

    private MQProducer producer;

    public ProducerShutdownHook(MQProducer producer) {
        this.producer = producer;
    }

    @Override
    public void run() {
        if (producer != null) {
            log.info("MQProducer shutdownHook");
            producer.shutdown();
        }
    }

}
