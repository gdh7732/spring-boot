package com.redis.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * redis测试
 *
 * @author guodahai
 * @version 2019/3/29 16:16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisStringTest {
    /**
     * RedisTemplate和StringRedisTemplate的区别：
     * 1. 两者的关系是StringRedisTemplate继承RedisTemplate。
     * 2. 两者的数据是不共通的；也就是说StringRedisTemplate只能管理StringRedisTemplate里面的数据，RedisTemplate只能管理RedisTemplate中的数据。
     * 3. SDR默认采用的序列化策略有两种，一种是String的序列化策略，一种是JDK的序列化策略。
     * StringRedisTemplate默认采用的是String的序列化策略，保存的key和value都是采用此策略序列化保存的。
     * RedisTemplate默认采用的是JDK的序列化策略，保存的key和value都是采用此策略序列化保存的。
     */
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * SET name "gdh" result: "name":"gdh"
     */
    @Test
    public void set() {
        redisTemplate.opsForValue().set("name", "gdh");
    }

    /**
     * SET name "gdh" result: name:"gdh"
     */
    @Test
    public void set2() {
        stringRedisTemplate.opsForValue().set("name", "gdh");
    }

    /**
     * GET name 获取指定 key 的值;
     */
    @Test
    public void get() {
        Object value = redisTemplate.opsForValue().get("name");
        System.out.println(value);
    }

    /**
     * GETRANGE key start end 返回 key 中字符串值的子字符;
     */
    @Test
    public void getRange() {
        Object value = redisTemplate.opsForValue().get("name", 0, 1);
        System.out.println(value);
    }

}