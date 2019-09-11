package com.ocean.boot.mq.base;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.selector.SelectMessageQueueByHash;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.ocean.boot.mq.annotation.RocketMQKey;
import com.ocean.boot.mq.constants.MessageConstant;
import com.ocean.boot.mq.enums.DelayTimeLevel;
import com.ocean.boot.mq.exception.RocketMqException;

/**
 * RocketMQ消息生产者基类
 */
@Slf4j
@Component
public abstract class AbstractRocketMQProducer {
    @Autowired
    protected DefaultMQProducer producer;

    private MessageQueueSelector messageQueueSelector = new SelectMessageQueueByHash();

    public AbstractRocketMQProducer() {

    }

    /**
     * fire and forget 不关心消息是否送达，可以提高发送tps
     * @param topic topic
     * @param tag tag
     * @param msgObj 消息内容
     * @throws RocketMqException 消息异常
     */
    public void sendOneWay(String topic, String tag, Object msgObj) throws RocketMqException {
        try {
            if(null == msgObj) {
                throw new RocketMqException("no message body to send");
            }
            producer.sendOneway(generateMessage(topic, tag, msgObj));
            log.info("Send oneway message:{}, topic:{}, tag:{}", msgObj, topic, tag);
        } catch (Exception e) {
            log.warn("Send oneway message fail, topic:{}, msgObj:{}", topic, msgObj, e);
            throw new RocketMqException(String.format("Send oneway message fail, topic: %s, tag: %s", topic, tag), e);
        }
    }

    /**
     * 发送顺序消息
     * @param topic topic
     * @param tag tag
     * @param msgObj 消息内容
     * @param hashKey 用于hash后选择queue的key
     */
    public void sendOneWayOrderly(String topic, String tag, Object msgObj, String hashKey) {
        if(null == msgObj) {
            throw new RocketMqException("no message body to send");
        }
        if(StringUtils.isEmpty(hashKey)) {
            // fall back to normal
            sendOneWay(topic, tag, msgObj);
        }
        try {
            producer.sendOneway(generateMessage(topic, tag, msgObj), messageQueueSelector, hashKey);
            log.info("Send oneway message orderly:{}, tag:{}, topic:{}, hashKey:{}", msgObj, topic, tag, hashKey);
        } catch (Exception e) {
            log.warn("Send oneway message orderly fail, topic:{}, tag:{}, hashKey:{}, msgObj:{}", topic, tag, msgObj, e);
            throw new RocketMqException(String.format("Send oneway message orderly fail, topic: %s, tag: %s, hashKey: %s", topic, tag, hashKey), e);
        }
    }

    /**
     * 同步发送消息
     * @param topic topic
     * @param tag tag
     * @param msgObj  消息内容
     * @throws RocketMqException 消息异常
     */
    public SendResult syncSend(String topic, String tag, Object msgObj) throws RocketMqException {
        try {
            if(null == msgObj) {
                throw new RocketMqException("no message body to send");
            }
            Message message = generateMessage(topic, tag, msgObj);
            SendResult sendResult = producer.send(message);
            log.info("Send sync topic:{} tag:{}, sendResult:{}", topic, tag, sendResult);
            this.doAfterSyncSend(sendResult);
            return sendResult;
        } catch (Exception e) {
            log.warn("Send sync message fail, topic:{}, tag:{}, msgObj:{}", topic, tag, msgObj, e);
            throw new RocketMqException(String.format("Send sync message fail, topic: %s, tag: %s", topic, tag), e);
        }
    }

    /**
     * 同步发送批量消息
     * @param topic topic
     * @param tag tag
     * @param msgObjs  批量消息(msg body length limit 128k, msg properties length limit 32k)
     * @throws RocketMqException 消息异常
     */
    public SendResult syncSendBatch(String topic, String tag, Collection<? extends Object> msgObjs) throws RocketMqException {
        try {
            if(null == msgObjs || msgObjs.isEmpty()) {
                throw new RocketMqException("no message body to send");
            }
            List<Message> messages = new ArrayList<>();
            for (Object msgObj : msgObjs) {
                messages.add(generateMessage(topic, tag, msgObj));
            }
            SendResult sendResult = producer.send(messages);
            log.info("Send sync batch msgs topic:{} tag:{}, sendResult:{}", topic, tag, sendResult);
            this.doAfterSyncSend(sendResult);
            return sendResult;
        } catch (Exception e) {
            log.warn("Send sync batch msgs fail, topic:{}, tag:{}, msgObj:{}", topic, tag, msgObjs, e);
            throw new RocketMqException(String.format("Send sync batch msgs fail, topic: %s, tag: %s", topic, tag), e);
        }
    }

    /**
     * 同步发送顺序消息
     * @param topic  topic
     * @param tag tag
     * @param msgObj  消息内容
     * @param hashKey  用于hash后选择queue的key
     * @throws RocketMqException 消息异常
     */
    public SendResult syncSendOrderly(String topic, String tag, Object msgObj, String hashKey) throws RocketMqException {
        if(null == msgObj) {
            throw new RocketMqException("no message body to send");
        }
        if(StringUtils.isEmpty(hashKey)) {
            // fall back to normal
            syncSend(topic, tag, msgObj);
        }
        try {
            SendResult sendResult = producer.send(generateMessage(topic, tag, msgObj), messageQueueSelector, hashKey);
            log.info("Send message orderly, topic:{} tag:{} hashKey:{}, sendResult:{}", topic, tag, hashKey, sendResult);
            this.doAfterSyncSend(sendResult);
            return sendResult;
        } catch (Exception e) {
            log.warn("Send message orderly fail, topic:{} tag:{} hashKey:{}, msgObj:{}", topic, tag, hashKey, msgObj, e);
            throw new RocketMqException(String.format("Send message orderly fail, topic: %s, tag: %s, hashKey: %s", topic, tag, hashKey), e);
        }
    }

    /**
     * 异步发送消息
     * @param topic topic
     * @param tag tag
     * @param msgObj 消息内容
     * @param sendCallback 回调
     * @throws RocketMqException 消息异常
     */
    public void asyncSend(String topic, String tag, Object msgObj, SendCallback sendCallback) throws RocketMqException {
        try {
            if (null == msgObj) {
                throw new RocketMqException("no message body to send");
            }
            producer.send(generateMessage(topic, tag, msgObj), sendCallback);
            log.info("Send message async, topic:{} tag:{}, msgObj:{}", topic, tag, msgObj);
        } catch (Exception e) {
            log.warn("Send message async fail, topic:{} tag:{}, msgObj:{}", topic, tag, msgObj, e);
            throw new RocketMqException(String.format("Send message async fail, topic: %s, tag: %s", topic, tag), e);
        }
    }

    /**
     * 异步发送消息
     * @param topic topic
     * @param tag tag
     * @param msgObj 消息内容
     * @param sendCallback 回调
     * @param hashKey 用于hash后选择queue的key
     * @throws RocketMqException 消息异常
     */
    public void asyncSend(String topic, String tag, Object msgObj, SendCallback sendCallback, String hashKey) throws RocketMqException {
        if (null == msgObj) {
            throw new RocketMqException("no message body to send");
        }
        if(StringUtils.isEmpty(hashKey)) {
            // fall back to normal
            asyncSend(topic, tag, msgObj, sendCallback);
        }
        try {
            producer.send(generateMessage(topic, tag, msgObj), messageQueueSelector, hashKey, sendCallback);
            log.info("Send message async, topic:{} tag:{} hashKey:{}, msgObj:{}", topic, tag, hashKey, msgObj);
        } catch (Exception e) {
            log.warn("Send message async fail, topic:{} tag:{} hashKey:{}, msgObj:{}", topic, tag, hashKey, msgObj, e);
            throw new RocketMqException(String.format("Send message async fail, topic: %s, tag: %s, hashKey: %s", topic, tag, hashKey), e);
        }
    }

    /**
     * 同步发送消息
     * @param topic topic
     * @param tag tag
     * @param msgObj  消息内容
     * @param delayTimeLevel 延迟时间
     * @throws RocketMqException 消息异常
     */
    public SendResult syncSendDelay(String topic, String tag, Object msgObj, DelayTimeLevel delayTimeLevel) throws RocketMqException {
        try {
            if(null == msgObj) {
                throw new RocketMqException("no message body to send");
            }
            Message message = generateMessage(topic, tag, msgObj);
            if(delayTimeLevel != null) {
                message.setDelayTimeLevel(delayTimeLevel.getLevel());
            }
            SendResult sendResult = producer.send(message);
            log.info("Send delayTime topic:{}, tag:{}, delayTime:{} msgId:{} {}", topic, tag, delayTimeLevel, sendResult.getMsgId(), sendResult);
            this.doAfterSyncSend(sendResult);
            return sendResult;
        } catch (Exception e) {
            log.warn("Send sync delayTime message fail, topic:{}, tag:{}, delayTime:{} msgObj:{}", topic, tag, delayTimeLevel, msgObj, e);
            throw new RocketMqException("Send sync delayTime message failed, topic:" + topic, e);
        }
    }

    /**
     * 重写此方法处理发送后的逻辑
     * @param sendResult  发送结果
     */
    public void doAfterSyncSend(SendResult sendResult) {}

    /**
     * 生成消息
     * @param topic 消息topic
     * @param tag 消息tag
     * @param msgObj 消息内容
     * @return
     */
    private Message generateMessage(String topic, String tag, Object msgObj) {
        String str = JSON.toJSONString(msgObj);
        if(StringUtils.isEmpty(topic)) {
            throw new RocketMqException("no topic defined to send this message");
        }
        Message message = new Message(topic, str.getBytes(Charset.forName(MessageConstant.DEFAULT_CHARSET)));
        if (!StringUtils.isEmpty(tag)) {
            message.setTags(tag);
        }
        String messageKey= "";
        try {
            Field[] fields = msgObj.getClass().getDeclaredFields();
            for (Field field : fields) {
                Annotation[] allFAnnos= field.getAnnotations();
                if(allFAnnos.length > 0) {
                    for (int i = 0; i < allFAnnos.length; i++) {
                        if(allFAnnos[i].annotationType().equals(RocketMQKey.class)) {
                            field.setAccessible(true);
                            RocketMQKey mqKey = RocketMQKey.class.cast(allFAnnos[i]);
                            if(field.get(msgObj) != null) {
                                messageKey = StringUtils.isEmpty(mqKey.prefix()) ? field.get(msgObj).toString() : (mqKey.prefix() + field.get(msgObj).toString());
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.warn("Parse messagekey fail", e);
        }
        if(StringUtils.isNotEmpty(messageKey)) {
            message.setKeys(messageKey);
        }
        return message;
    }

}