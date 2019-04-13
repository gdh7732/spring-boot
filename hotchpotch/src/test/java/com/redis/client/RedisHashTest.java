package com.redis.client;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author guodahai
 * @version 2019/4/12 16:45
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisHashTest {
    @Autowired
    protected RedisClient redisClient;
    /**
     * 获取存储在哈希表中指定字段的值
     */
    @Test
    public void hGet() {
        Object result = redisClient.hGet("hash", "age");
        System.out.println(result);
    }

    /**
     * 获取所有字段的值
     */
    @Test
    public void hGetAll() {
        Map<Object, Object> result = redisClient.hGetAll("hash");
        System.out.println(result.get("name"));
    }

    /**
     * 获取所有给定字段的值
     */
    @Test
    public void hMultiGet() {
        List<Object> result = redisClient.hMultiGet("hash", Lists.newArrayList("age", "name"));
        System.out.println(result);
    }

    @Test
    public void hPut() {
        redisClient.hPut("hash","age",25);
        redisClient.hPut("hash","name","gdh");
    }

    @Test
    public void hPutAll() {
        Map<String, Object> map = Maps.newHashMap();
        map.put("age",1);
        map.put("name","gdh");
        redisClient.hPutAll("hash-map",map);
    }

    /**
     * 仅当hashKey不存在时才设置
     */
    @Test
    public void hPutIfAbsent() {
        Boolean result = redisClient.hPutIfAbsent("hash", "age", 1);
        System.out.println(result);
    }

    /**
     * 删除一个或多个哈希表字段
     */
    @Test
    public void hDelete() {
        Long result = redisClient.hDelete("hash", "age");
        System.out.println(result);
    }

    /**
     * 查看哈希表 key 中，指定的字段是否存在
     */
    @Test
    public void hExists() {
        boolean result = redisClient.hExists("hash", "age");
        System.out.println(result);
    }

    /**
     * 为哈希表 key 中的指定字段的整数值加上增量 increment
     */
    @Test
    public void hIncrBy() {
        Long result = redisClient.hIncrBy("hash", "age", 2);
        System.out.println(result);
    }

    /**
     * 为哈希表 key 中的指定字段的整数值加上增量 increment
     */
    @Test
    public void hIncrByFloat() {
        Double result = redisClient.hIncrByFloat("hash", "age", 0.5);
        System.out.println(result);
    }

    /**
     * 获取所有哈希表中的字段
     */
    @Test
    public void hKeys() {
        Set<Object> result = redisClient.hKeys("hash");
        System.out.println(result);
    }

    /**
     * 获取哈希表中字段的数量
     */
    @Test
    public void hSize() {
        Long result = redisClient.hSize("hash");
        System.out.println(result);
    }

    /**
     * 获取哈希表中所有值
     */
    @Test
    public void hValues() {
        List<Object> result = redisClient.hValues("hash");
        System.out.println(result);
    }

    /**
     * 遍历
     */
    @Test
    public void hScan() {
        Cursor<Map.Entry<Object, Object>> result = redisClient.hScan("hash", ScanOptions.NONE);
        while (result.hasNext()) {
            Map.Entry<Object, Object> entry = result.next();
            System.out.println("key:" + entry.getKey());
            System.out.println("value:" + entry.getValue());
        }
    }
}