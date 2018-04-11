package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 连接池配置类
 *
 * @author guodahai
 * @version 2018/4/11 下午2:05
 */
@Configuration
public class TaskThreadPoolConfig {

    @Value("${task.core.pool.size}")
    private int corePoolSize;

    @Value("${task.max.pool.size}")
    private int maxPoolSize;

    @Value("${task.queue.capacity}")
    private int keepAliveSeconds;

    @Value("${task.keep.alive.seconds}")
    private int queueCapacity;

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public int getKeepAliveSeconds() {
        return keepAliveSeconds;
    }

    public void setKeepAliveSeconds(int keepAliveSeconds) {
        this.keepAliveSeconds = keepAliveSeconds;
    }

    public int getQueueCapacity() {
        return queueCapacity;
    }

    public void setQueueCapacity(int queueCapacity) {
        this.queueCapacity = queueCapacity;
    }
}
