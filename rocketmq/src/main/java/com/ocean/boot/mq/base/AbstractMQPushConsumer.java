package com.ocean.boot.mq.base;

import com.ocean.boot.mq.config.ConsumerProperties;
import com.ocean.boot.mq.hook.ConsumeOneMessageAdvice;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.ArrayList;
import java.util.List;

/**
 * RocketMQ消费者(Push模式)
 *
 * @author ocean
 */
@Slf4j
public abstract class AbstractMQPushConsumer<T> extends AbstractRocketMQConsumer<T> {
    @Getter
    @Setter
    protected DefaultMQPushConsumer consumer;
    @Getter
    @Setter
    private ConsumerProperties consumerProperties;

    private static final ArrayList<ConsumeOneMessageAdvice> consumeMessageHookList = new ArrayList<ConsumeOneMessageAdvice>();

    public AbstractMQPushConsumer() {
    }

    /**
     * 重写此方法，实现消息的具体处理
     * 注意方法异常重试
     *
     * @param message    消息范型
     * @param messageExt 消息
     * @return 处理结果
     */
    public abstract boolean processMessage(T message, MessageExt messageExt);

    /**
     * 消费普通消息，可重写自定义序列化和返回消费成功的相关逻辑
     *
     * @param msgExts                    消息列表
     * @param consumeConcurrentlyContext 并发消费上下文
     * @return 消费状态
     */
    public ConsumeConcurrentlyStatus dealMessage(List<MessageExt> msgExts, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        for (MessageExt messageExt : msgExts) {
            if (messageExt.getReconsumeTimes() != 0) {
                log.info("Reconsume times:{}, msgId:{}", messageExt.getReconsumeTimes(), messageExt.getMsgId());
            }
            log.info("Receive msgId:{}, topic:{}, tag:{}", messageExt.getMsgId(), messageExt.getTopic(), messageExt.getTags());
            // 重试次数
            int retryTimes = consumerProperties.getRetryTimesWhenConsumeFailed();
            try {
                if (hasAdvice()) {
                    executeAdviceBefore(messageExt);
                }
                T t = parseMessage(messageExt);
                if (null != t && !processMessage(t, messageExt)) {
                    if (messageExt.getReconsumeTimes() < retryTimes) {
                        // 进行日志记录
                        log.warn("Consume concurrently fail, ask for reconsume msgId:{}", messageExt.getMsgId());
                        return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                    } else {
                        // 消费失败并达到重试次数，记录日志
                        log.warn("Consume concurrently fail, reach retryTimes:{} msgId:{}", retryTimes, messageExt.getMsgId());
                    }
                }
            } catch (Exception e) {
                // 进行异常日志记录
                log.warn("Consume concurrently exception, msgId:{}", messageExt.getMsgId(), e);
                if (messageExt.getReconsumeTimes() < retryTimes) {
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
            } finally {
                if (hasAdvice()) {
                    executeAdviceAfter(messageExt);
                }
            }
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

    /**
     * 消费顺序消息，可重写自定义序列化和返回消费成功的相关逻辑
     *
     * @param msgExts               消息列表
     * @param consumeOrderlyContext 顺序消费上下文
     * @return 处理结果
     */
    public ConsumeOrderlyStatus dealMessage(List<MessageExt> msgExts, ConsumeOrderlyContext consumeOrderlyContext) {
        for (MessageExt messageExt : msgExts) {
            if (messageExt.getReconsumeTimes() != 0) {
                log.info("Reconsume times:{}, orderly msgId:{}", messageExt.getReconsumeTimes(), messageExt.getMsgId());
            }
            log.info("Receive orderly msgId:{}, topic:{}, tag:{}", messageExt.getMsgId(), messageExt.getTopic(), messageExt.getTags());
            try {
                if (hasAdvice()) {
                    executeAdviceBefore(messageExt);
                }
                T t = parseMessage(messageExt);
                if (null != t && !processMessage(t, messageExt)) {
                    // 消费失败记录日志
                    log.warn("Consume orderly fail, ask for reconsume msgId:{}", messageExt.getMsgId());
                    return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
                }
            } catch (Exception e) {
                // 异常日志记录
                log.warn("Consume orderly exception, msgId:{}", messageExt.getMsgId(), e);
                return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
            } finally {
                if (hasAdvice()) {
                    executeAdviceAfter(messageExt);
                }
            }
        }
        return ConsumeOrderlyStatus.SUCCESS;
    }

    public void executeAdviceAfter(final MessageExt msg) {
        if (!consumeMessageHookList.isEmpty()) {
            for (ConsumeOneMessageAdvice hook : consumeMessageHookList) {
                try {
                    hook.consumeMessageAfter(msg);
                } catch (Throwable e) {
                }
            }
        }
    }

    public void executeAdviceBefore(final MessageExt msg) {
        if (!consumeMessageHookList.isEmpty()) {
            for (ConsumeOneMessageAdvice hook : consumeMessageHookList) {
                try {
                    hook.consumeMessageBefore(msg);
                } catch (Throwable e) {
                }
            }
        }
    }

    public boolean hasAdvice() {
        return !consumeMessageHookList.isEmpty();
    }

    public static void registerOneMessageAdvice(ConsumeOneMessageAdvice hook) {
        consumeMessageHookList.add(hook);
        log.info("register ConsumeOneMessageAdvice, {}", hook.hookName());
    }

}
