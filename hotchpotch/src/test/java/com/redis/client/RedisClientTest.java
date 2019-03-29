package com.redis.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * redis测试
 *
 * @author guodahai
 * @version 2019/3/29 16:16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisClientTest {

    @Autowired
    private RedisClient redisClient;

    @Test
    public void expire() {
    }

    @Test
    public void getExpire() {
    }

    @Test
    public void hasKey() {
    }

    @Test
    public void del() {
    }

    @Test
    public void get() {
        System.out.println(redisClient.get("test1"));
    }

    @Test
    public void set() {
        redisClient.set("test1", "gdh");
    }

    @Test
    public void set1() {
    }

    @Test
    public void increment() {
    }

    @Test
    public void decrement() {
    }

    @Test
    public void hGet() {
    }

    @Test
    public void hmGet() {
    }

    @Test
    public void hmSet() {
    }

    @Test
    public void hmSet1() {
    }

    @Test
    public void hSet() {
    }

    @Test
    public void hSet1() {
    }

    @Test
    public void hDelete() {
    }

    @Test
    public void hHasKey() {
    }

    @Test
    public void hIncrement() {
    }

    @Test
    public void hDecrement() {
    }

    @Test
    public void sGet() {
    }

    @Test
    public void sHasKey() {
    }

    @Test
    public void sSet() {
    }

    @Test
    public void sSetAndTime() {
    }

    @Test
    public void sGetSetSize() {
    }

    @Test
    public void setRemove() {
    }

    @Test
    public void lGet() {
    }

    @Test
    public void lGetListSize() {
    }

    @Test
    public void lGetIndex() {
    }

    @Test
    public void lSet() {
    }

    @Test
    public void lSet1() {
    }

    @Test
    public void lSet2() {
    }

    @Test
    public void lSet3() {
    }

    @Test
    public void lUpdateIndex() {
    }

    @Test
    public void lRemove() {
    }
}