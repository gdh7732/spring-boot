package com.redis.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author guodahai
 * @version 2019/4/12 16:45
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisZsetTest {
    @Autowired
    protected RedisClient redisClient;

    @Test
    public void zAdd() {
        Boolean result = redisClient.zAdd("zSet", "a", 1.0);
        System.out.println(result);
    }

    @Test
    public void zAdd1() {
    }

    @Test
    public void zRemove() {
    }

    @Test
    public void zIncrementScore() {
    }

    @Test
    public void zRank() {
    }

    @Test
    public void zReverseRank() {
    }

    @Test
    public void zRange() {
    }

    @Test
    public void zRangeWithScores() {
    }

    @Test
    public void zRangeByScore() {
    }

    @Test
    public void zRangeByScoreWithScores() {
    }

    @Test
    public void zRangeByScoreWithScores1() {
    }

    @Test
    public void zReverseRange() {
    }

    @Test
    public void zReverseRangeWithScores() {
    }

    @Test
    public void zReverseRangeByScore() {
    }

    @Test
    public void zReverseRangeByScoreWithScores() {
    }

    @Test
    public void zReverseRangeByScore1() {
    }

    @Test
    public void zCount() {
    }

    @Test
    public void zSize() {
    }

    @Test
    public void zZCard() {
    }

    @Test
    public void zScore() {
    }

    @Test
    public void zRemoveRange() {
    }

    @Test
    public void zRemoveRangeByScore() {
    }

    @Test
    public void zUnionAndStore() {
    }

    @Test
    public void zUnionAndStore1() {
    }

    @Test
    public void zIntersectAndStore() {
    }

    @Test
    public void zIntersectAndStore1() {
    }

    @Test
    public void zScan() {
    }
}