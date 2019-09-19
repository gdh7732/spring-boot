package com.ocean.rocket.producer;

import com.ocean.rocket.RocketMqProducerApplication;
import lombok.extern.slf4j.Slf4j;
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

    @Test
    public void test() {
        producer.syncSend("topic", null, "message");
    }

}