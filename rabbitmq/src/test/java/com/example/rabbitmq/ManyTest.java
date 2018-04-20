package com.example.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManyTest {
    @Autowired
    private NeoSenderOne senderOne;

    @Autowired
    private NeoSenderTwo senderTwo;

    @Test
    public void oneToMany() throws Exception {
        for (int i = 0; i < 100; i++) {
            senderOne.send(i);
        }
    }

    @Test
    public void manyToMany() throws Exception {
        for (int i = 0; i < 100; i++) {
            senderOne.send(i);
            senderTwo.send(i);
        }
    }

}