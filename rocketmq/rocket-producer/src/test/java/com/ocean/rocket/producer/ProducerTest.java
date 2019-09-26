package com.ocean.rocket.producer;

import com.alibaba.fastjson.JSON;
import com.ocean.rocket.RocketMqProducerApplication;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RocketMqProducerApplication.class)
public class ProducerTest {

    @Resource
    private Producer producer;

    /**
     * 发送同步消息
     */
    @Test
    public void test() {
        SendResult sendResult = producer.syncSend("topic", null, "message");
        log.info(JSON.toJSONString(sendResult));
    }

    /**
     * 发送事务消息
     *
     * @throws MQClientException
     */
    @Test
    public void sendMessageInTransaction() throws MQClientException {
        String str = "";
        producer.sendMessageInTransaction("topic", null, "message", str, new TransactionListener() {

            @Override
            public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
                //TODO 执行本地事务
                if (StringUtils.isEmpty(str)) {
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                }
                return LocalTransactionState.COMMIT_MESSAGE;
            }

            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt msg) {
                //TODO 检查本地事务
                return LocalTransactionState.COMMIT_MESSAGE;
            }
        });
    }

}