package com.ocean.rocket.enums;

/**
 * 消费类型
 *
 * @author 003238
 */
public enum ConsumeMode {
    /**
     * consume delivered messages concurrently
     */
    CONCURRENTLY,

    /**
     * consume delivered messages orderly, one queue, one thread
     */
    ORDERLY
}
