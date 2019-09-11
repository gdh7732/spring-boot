package com.ocean.boot.mq.hook;

import org.apache.rocketmq.common.message.MessageExt;

/**
 * @author 003238
 */
public interface ConsumeOneMessageAdvice {
    String hookName();

    void consumeMessageBefore(final MessageExt msg);

    void consumeMessageAfter(final MessageExt msg);
}
