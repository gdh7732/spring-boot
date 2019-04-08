package com.redis.client;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * redis测试
 * 参考文章：https://www.jianshu.com/p/19e851a3edba
 *
 * @author guodahai
 * @version 2019/3/29 16:16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisKeyTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * EXISTS name result: 0 or 1
     * 检查给定 key 是否存在
     */
    @Test
    public void exit() {
        redisTemplate.opsForValue().set("name", "ocean");
        Boolean result = redisTemplate.hasKey("name");
        System.out.println(result);
    }

    /**
     * DEL name result: 0 or 1
     * 该命令用于在 key 存在时删除 key
     */
    @Test
    public void delete() {
        redisTemplate.delete("name");
    }

    /**
     * EXPIRE key seconds 为给定 key 设置过期时间，以秒计;
     * PEXPIRE key milliseconds 设置 key 的过期时间以毫秒计
     */
    @Test
    public void expire() {
        Boolean result = redisTemplate.expire("name", 3, TimeUnit.SECONDS);
        System.out.println(result);
    }

    /**
     * EXPIREAT key timestamp EXPIREAT 的作用和 EXPIRE 类似，都用于为 key 设置过期时间。 不同在于 EXPIREAT 命令接受的时间参数是 UNIX 时间戳(unix timestamp);
     */
    @Test
    public void expireAt() {
        Boolean result = redisTemplate.expireAt("name", new Date());
        System.out.println(result);
    }

    /**
     * PTTL key 以毫秒为单位返回 key 的剩余的过期时间;
     * TTL key 以秒为单位，返回给定 key 的剩余生存时间(TTL, time to live);
     */
    @Test
    public void getExpire() {
        Long result = redisTemplate.getExpire("name", TimeUnit.SECONDS);
        System.out.println(result);
    }

    /**
     * TYPE key 返回 key 所储存的值的类型;
     */
    @Test
    public void getType() {
        DataType name = redisTemplate.type("name");
        System.out.println(name);
    }

    /**
     * TYPE key 返回 key 所储存的值的类型;
     */
    @Test
    public void randomKey() {
        String key = redisTemplate.randomKey();
        System.out.println(key);
    }

    /**
     * RENAME key newkey 修改 key 的名称;
     */
    @Test
    public void rename() {
        redisTemplate.rename("name", "name1");
    }

}