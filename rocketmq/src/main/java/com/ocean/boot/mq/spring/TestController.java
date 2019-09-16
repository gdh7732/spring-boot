package com.ocean.boot.mq.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 生产者
 *
 * @author ocean
 */

@RestController
public class TestController {
    @Autowired
    private Producer rocketMQProducer;

    @RequestMapping("/push/{msg}")
    public String pushMsg(@PathVariable("msg") String msg) {
        try {
            rocketMQProducer.sendOneWay("PushTopic", "push", msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ERROR";
    }
}
