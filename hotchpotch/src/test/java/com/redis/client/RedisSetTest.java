package com.redis.client;

import com.example.common.BaseResult;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;

/**
 * @author guodahai
 * @version 2019/4/12 16:45
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisSetTest {
    @Autowired
    protected RedisClient redisClient;

    /**
     * set添加元素
     */
    @Test
    public void sAdd() {
        Long result = redisClient.sAdd("set-obj", new BaseResult(), new BaseResult(), new BaseResult(), new BaseResult());
        System.out.println(result);
    }

    /**
     * set移除元素
     */
    @Test
    public void sRemove() {
        Long result = redisClient.sRemove("set", 1);
        System.out.println(result);
    }

    /**
     * 移除并返回集合的一个随机元素
     */
    @Test
    public void sPop() {
        Object result = redisClient.sPop("set");
        System.out.println(result);
    }

    /**
     * 将元素value从一个集合移到另一个集合
     */
    @Test
    public void sMove() {
        Boolean result = redisClient.sMove("set-int", 1, "set");
        System.out.println(result);
    }

    /**
     * 获取集合的大小
     */
    @Test
    public void sSize() {
        Long result = redisClient.sSize("set-int");
        System.out.println(result);
    }

    /**
     * 判断集合是否包含value
     */
    @Test
    public void sIsMember() {
        Boolean result = redisClient.sIsMember("set-int", 2);
        System.out.println(result);
    }

    /**
     * 取两个集合的交集
     */
    @Test
    public void sIntersect() {
        Set<Object> result = redisClient.sIntersect("set", "set-int");
        System.out.println(result);
    }

    /**
     * 获取key集合与多个集合的交集
     */
    @Test
    public void sIntersect1() {
        Set<Object> result = redisClient.sIntersect("set", Lists.newArrayList("set-obj", "set-int"));
        System.out.println(result);
    }

    /**
     * key集合与otherKey集合的交集存储到destKey集合中
     */
    @Test
    public void sIntersectAndStore() {
        Long result = redisClient.sIntersectAndStore("set", "set-int", "set-and");
        System.out.println(result);
    }

    @Test
    public void sIntersectAndStore1() {
        Long result = redisClient.sIntersectAndStore("set", Lists.newArrayList("set-obj", "set-int"), "set-and");
        System.out.println(result);
    }

    /**
     * 取并集
     */
    @Test
    public void sUnion() {
        Set<Object> result = redisClient.sUnion("set", "set-int");
        System.out.println(result);
    }

    @Test
    public void sUnion1() {
        Set<Object> result = redisClient.sUnion("set", Lists.newArrayList("set-obj", "set-int"));
        System.out.println(result);
    }

    @Test
    public void sUnionAndStore() {
        Long result = redisClient.sUnionAndStore("set",  "set-int", "set-or");
        System.out.println(result);
    }

    @Test
    public void sUnionAndStore1() {
        Long result = redisClient.sUnionAndStore("set", Lists.newArrayList("set-obj", "set-int"), "set-or");
        System.out.println(result);
    }

    /**
     * 取差集
     */
    @Test
    public void sDifference1() {
        Set<Object> result = redisClient.sDifference("set", "set-int");
        System.out.println(result);
    }

    @Test
    public void sDifference2() {
        Set<Object> result = redisClient.sDifference("set", Lists.newArrayList("set-obj", "set-int"));
        System.out.println(result);
    }

    @Test
    public void sDifference3() {
        Long result = redisClient.sDifference("set",  "set-int", "set-or");
        System.out.println(result);
    }

    @Test
    public void sDifference4() {
        Long result = redisClient.sDifference("set", Lists.newArrayList("set-obj", "set-int"), "set-or");
        System.out.println(result);
    }

    /**
     * 获取所有元素
     */
    @Test
    public void setMembers() {
        Set<Object> result = redisClient.getMembers("set");
        System.out.println(result);
    }

    /**
     * 随机获取集合中的一个元素
     */
    @Test
    public void sRandomMember() {
        Object result = redisClient.sRandomMember("set");
        System.out.println(result);
    }

    /**
     * 随机获取集合中count个元素
     */
    @Test
    public void sRandomMembers() {
        List<Object> result = redisClient.sRandomMembers("set", 5L);
        System.out.println(result);
    }

    /**
     * 随机获取集合中count个元素并且去除重复的
     */
    @Test
    public void sDistinctRandomMembers() {
        Set<Object> result = redisClient.sDistinctRandomMembers("set", 2L);
        System.out.println(result);
    }

    /**
     * 遍历
     */
    @Test
    public void sScan() {
        Cursor<Object> result = redisClient.sScan("set", ScanOptions.NONE);
        System.out.println(result);
        while (result.hasNext()) {
            System.out.println(result.next());
        }
    }
}