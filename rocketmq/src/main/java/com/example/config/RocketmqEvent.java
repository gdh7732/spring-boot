package com.example.config;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.common.message.MessageExt;
import org.springframework.context.ApplicationEvent;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author guodahai
 * @version 2018/5/24 下午5:27
 */
public class RocketmqEvent extends ApplicationEvent {
    private static final long serialVersionUID = -4468405250074063206L;

    private DefaultMQPushConsumer consumer;
    private MessageExt messageExt;
    private String topic;
    private String tag;
    private byte[] body;

    public RocketmqEvent(MessageExt msg,DefaultMQPushConsumer consumer) throws Exception {
        super(msg);
        this.topic = msg.getTopic();
        this.tag = msg.getTags();
        this.body = msg.getBody();
        this.consumer = consumer;
        this.messageExt = msg;
    }

    public String getMsg() {
        try {
            return new String(this.body,"utf-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public String getMsg(String code) {
        try {
            return new String(this.body,code);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public DefaultMQPushConsumer getConsumer() {
        return consumer;
    }

    public void setConsumer(DefaultMQPushConsumer consumer) {
        this.consumer = consumer;
    }

    public MessageExt getMessageExt() {
        return messageExt;
    }

    public void setMessageExt(MessageExt messageExt) {
        this.messageExt = messageExt;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RocketmqEvent that = (RocketmqEvent) o;
        return Objects.equals(consumer, that.consumer) &&
                Objects.equals(messageExt, that.messageExt) &&
                Objects.equals(topic, that.topic) &&
                Objects.equals(tag, that.tag) &&
                Arrays.equals(body, that.body);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(consumer, messageExt, topic, tag);
        result = 31 * result + Arrays.hashCode(body);
        return result;
    }
}
