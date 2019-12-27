package com.ocean.strategy;

/**
 * 策略接口
 * 查询折扣的接口
 *
 * @author 郭大海
 */
public interface Strategy {
    /**
     * 查询折扣
     *
     * @return 折扣率
     */
    Double discount();
}
