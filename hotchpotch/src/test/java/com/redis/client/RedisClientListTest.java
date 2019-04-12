package com.redis.client;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.common.BaseResult;
import com.google.common.collect.Lists;

/**
 * @author guodahai
 * @version 2019/4/12 16:45
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisClientListTest {
    @Autowired
    protected RedisClient redisClient;

    /**
     * left:表示队列头部
     * right:表示队列尾部
     * pop:弹出
     * push:储存
     */

    /**
     * 通过索引获取列表中的元素（get）
     */
    @Test
    public void lIndex() {
        Object result = redisClient.lIndex("list", 0);
        System.out.println(result);
    }

    /**
     * 通过索引设置列表元素的值（set）
     */
    @Test
    public void lSet() {
        redisClient.lSet("list", 0, "a");
    }

    /**
     * 获取列表指定范围内的元素
     */
    @Test
    public void lRange() {
        List<Object> list = redisClient.lRange("list", 0, 2);
        System.out.println(list);
    }

    /**
     * 存储在list头部
     */
    @Test
    public void lLeftPush() {
        redisClient.lLeftPush("list", new BaseResult());
    }

    /**
     * 批量存储在list头部
     */
    @Test
    public void lLeftPushAll() {
        redisClient.lLeftPushAll("list-int", 1, 2, 3);
    }

    /**
     * 批量存储在list头部(返回总size)
     */
    @Test
    public void lLeftPushAll1() {
        Long result = redisClient.lLeftPushAll("list-int", Lists.newArrayList(1, 2, 3, 4, 5));
        System.out.println(result);
    }

    /**
     * 当list存在的时候才加入
     */
    @Test
    public void lLeftPushIfPresent() {
        Long result = redisClient.lLeftPushIfPresent("list", 1);
        System.out.println(result);
    }

    /**
     * 如果pivot存在,再pivot前面添加
     */
    @Test
    public void lLeftPush1() {
        Long result = redisClient.lLeftPush("list", 2,2);
        System.out.println(result);
    }

    /**
     * 存储在list尾部
     */
    @Test
    public void lRightPush() {
        redisClient.lRightPush("list", new BaseResult());
    }

    /**
     * 批量存储在list尾部
     */
    @Test
    public void lRightPushAll() {
        redisClient.lRightPushAll("list-int", 1, 2, 3);
    }

    /**
     * 批量存储在list尾部(返回总size)
     */
    @Test
    public void lRightPushAll1() {
        Long result = redisClient.lRightPushAll("list-int", Lists.newArrayList(1, 2, 3, 4, 5));
        System.out.println(result);
    }

    /**
     * 当list存在的时候才加入
     */
    @Test
    public void lRightPushIfPresent() {
        Long result = redisClient.lRightPushIfPresent("list", 1);
        System.out.println(result);
    }

    /**
     * 如果pivot存在,再pivot后面添加
     */
    @Test
    public void lRightPush1() {
        Long result = redisClient.lRightPush("list", 2,2);
        System.out.println(result);
    }


    /**
     * 移出并获取列表的第一个元素
     */
    @Test
    public void lLeftPop() {
        Object result = redisClient.lLeftPop("list-int");
        System.out.println(result);
    }

    /**
     * 移出并获取列表的第一个元素
     * 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
     */
    @Test
    public void lBLeftPop() {
        Object result = redisClient.lBLeftPop("list-int", 3000, TimeUnit.MILLISECONDS);
        System.out.println(result);
    }

    /**
     * 移除并获取列表最后一个元素
     */
    @Test
    public void lRightPop() {
        Object result = redisClient.lRightPop("list-int");
        System.out.println(result);
    }

    /**
     * 移出并获取列表的最后一个元素，
     * 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
     */
    @Test
    public void lBRightPop() {
        Object result = redisClient.lBRightPop("list-int", 3000, TimeUnit.MILLISECONDS);
        System.out.println(result);
    }

    /**
     * 移除列表的最后一个元素，并将该元素添加到另一个列表并返回
     */
    @Test
    public void lRightPopAndLeftPush() {
        Object result = redisClient.lRightPopAndLeftPush("list-str", "list-int");
        System.out.println(result);
    }

    /**
     * 从列表中弹出一个值(尾部)，将弹出的元素插入到另外一个列表（头部）中并返回它；
     * 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
     */
    @Test
    public void lBRightPopAndLeftPush() {
        Object result = redisClient.lBRightPopAndLeftPush("list-str", "list-int", 3000, TimeUnit.MILLISECONDS);
        System.out.println(result);
    }

    /**
     * 删除集合中值等于value得元素
     */
    @Test
    public void lRemove() {
        Long result = redisClient.lRemove("list-all", 0, "b");
        System.out.println(result);
    }

    /**
     * TODO 裁剪list
     */
    @Test
    public void lTrim() {
        redisClient.lTrim("list-all", 0, 1);
    }

    /**
     * 获取列表长度
     */
    @Test
    public void lLen() {
        Long size = redisClient.lLen("list-all");
        System.out.println(size);
    }
}