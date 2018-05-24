package com.example.config;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.TransactionMQProducer;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static com.example.config.RocketmqProperties.PREFIX;

/**
 * @author guodahai
 * @version 2018/5/24 下午4:41
 */
@Configuration
@EnableConfigurationProperties(RocketmqProperties.class)
@ConditionalOnProperty(prefix = PREFIX, value = "namesrvAddr")
public class RocketmqAutoConfiguration {

    @Autowired
    private RocketmqProperties properties;

    @Value("${spring.application.name}")
    private String producerGroupName;

    @Value("${spring.application.name}")
    private String consumerGroupName;

    @Autowired
    private ApplicationEventPublisher publisher;

    /**
     * 初始化向rocketmq发送普通消息的生产者
     */
    @Bean
    @ConditionalOnProperty(prefix = PREFIX, value = "producer.instanceName")
    public DefaultMQProducer defaultProducer() throws MQClientException {
        /**
         * 一个应用创建一个Producer，由应用来维护此对象，可以设置为全局对象或者单例<br>
         * 注意：ProducerGroupName需要由应用来保证唯一<br>
         * ProducerGroup这个概念发送普通的消息时，作用不大，但是发送分布式事务消息时，比较关键，
         * 因为服务器会回查这个Group下的任意一个Producer
         */
        DefaultMQProducer producer = new DefaultMQProducer(producerGroupName);
        producer.setNamesrvAddr(properties.getNamesrvAddr());
        producer.setInstanceName(properties.getProducer().getInstanceName());
//        producer.setVipChannelEnabled(false);

        /**
         * Producer对象在使用之前必须要调用start初始化，初始化一次即可<br>
         * 注意：切记不可以在每次发送消息时，都调用start方法
         */
        producer.start();
        System.out.println("RocketMq defaultProducer Started.");
        return producer;
    }

    /**
     * 初始化向rocketmq发送事务消息的生产者
     */
    @Bean
    @ConditionalOnProperty(prefix = PREFIX, value = "producer.tranInstanceName")
    public TransactionMQProducer transactionProducer() throws MQClientException {
        /**
         * 一个应用创建一个Producer，由应用来维护此对象，可以设置为全局对象或者单例<br>
         * 注意：ProducerGroupName需要由应用来保证唯一<br>
         * ProducerGroup这个概念发送普通的消息时，作用不大，但是发送分布式事务消息时，比较关键，
         * 因为服务器会回查这个Group下的任意一个Producer
         */
        TransactionMQProducer producer = new TransactionMQProducer("TransactionProducerGroupName");
        producer.setNamesrvAddr(properties.getNamesrvAddr());
        producer.setInstanceName(properties.getProducer().getTranInstanceName());

        // 事务回查最小并发数
        producer.setCheckThreadPoolMinSize(2);
        // 事务回查最大并发数
        producer.setCheckThreadPoolMaxSize(2);
        // 队列数
        producer.setCheckRequestHoldMax(2000);

        //TODO 由于社区版本的服务器阉割调了消息回查的功能，所以这个地方没有意义
        //TransactionCheckListener transactionCheckListener = new TransactionCheckListenerImpl();
        //producer.setTransactionCheckListener(transactionCheckListener);

        /**
         * Producer对象在使用之前必须要调用start初始化，初始化一次即可<br>
         * 注意：切记不可以在每次发送消息时，都调用start方法
         */
        producer.start();

        System.out.println("RocketMq TransactionMQProducer Started.");
        return producer;
    }

    /**
     * 初始化rocketmq消息监听方式的消费者
     */
    @Bean
    @ConditionalOnProperty(prefix = PREFIX, value = "consumer.instanceName")
    public DefaultMQPushConsumer pushConsumer() throws MQClientException {
        /**
         * 一个应用创建一个Consumer，由应用来维护此对象，可以设置为全局对象或者单例<br>
         * 注意：ConsumerGroupName需要由应用来保证唯一
         */
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerGroupName);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.setNamesrvAddr(properties.getNamesrvAddr());
        consumer.setInstanceName(properties.getConsumer().getInstanceName());
        consumer.setConsumeMessageBatchMaxSize(1);//设置批量消费，以提升消费吞吐量，默认是1


        /**
         * 订阅指定topic下tags
         */
//        List<String> subscribeList = properties.getConsumer().getSubscribe();
//        for (String sunscribe : subscribeList) {
//            consumer.subscribe(sunscribe.split(":")[0], sunscribe.split(":")[1]);
//        }
//
//        consumer.registerMessageListener((List<MessageExt> msgs, ConsumeConcurrentlyContext context) -> {
//
//            MessageExt msg = msgs.get(0);
//
//            try {
//                //默认msgs里只有一条消息，可以通过设置consumeMessageBatchMaxSize参数来批量接收消息
//                System.out.println(Thread.currentThread().getName() + " Receive New Messages: " + msgs.size());
//                //发布消息到达的事件，以便分发到每个tag的监听方法
//                this.publisher.publishEvent(new RocketmqEvent(msg, consumer));
//                System.out.println("消息到达事件已经发布成功！");
//            } catch (Exception e) {
//                e.printStackTrace();
//                if (msg.getReconsumeTimes() <= 3) {//重复消费3次
//                    //TODO 进行日志记录
//                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
//                } else {
//                    //TODO 消息消费失败，进行日志记录
//                }
//            }
//
//            //如果没有return success，consumer会重复消费此信息，直到success。
//            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);//延迟5秒再启动，主要是等待spring事件监听相关程序初始化完成，否则，回出现对RocketMQ的消息进行消费后立即发布消息到达的事件，然而此事件的监听程序还未初始化，从而造成消息的丢失
                    /**
                     * Consumer对象在使用之前必须要调用start初始化，初始化一次即可<br>
                     */
                    try {
                        consumer.start();
                    } catch (Exception e) {
                        System.out.println("RocketMq pushConsumer Start failure!!!.");
                        e.printStackTrace();
                    }

                    System.out.println("RocketMq pushConsumer Started.");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();

        return consumer;
    }

}