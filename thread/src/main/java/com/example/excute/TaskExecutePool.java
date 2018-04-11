package com.example.excute;

import com.example.config.TaskThreadPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.RejectedExecutionHandler;

/**
 * 自定义连接池
 *
 * @author guodahai
 * @version 2018/4/11 下午2:01
 */
@Configuration
@EnableAsync
public class TaskExecutePool {

    private Logger logger = LoggerFactory.getLogger(TaskExecutePool.class);

    @Autowired
    private TaskThreadPoolConfig config;

    @Bean
    public ThreadPoolTaskExecutor myTaskAsyncPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(config.getCorePoolSize());
        executor.setMaxPoolSize(config.getMaxPoolSize());
        executor.setQueueCapacity(config.getQueueCapacity());
        executor.setKeepAliveSeconds(config.getKeepAliveSeconds());
        executor.setThreadNamePrefix("MyExecutor-");
        executor.setRejectedExecutionHandler(myRejectedExecutionHandler());
        executor.initialize();
        return executor;
    }

    /**
     * 自定义调度器
     *
     * @return
     */
    public RejectedExecutionHandler myRejectedExecutionHandler() {
        return (r, executor) -> {
            logger.info("添加新的任务到线程池失败，正在尝试重新添加。。。");
            try {
                Thread.sleep(100);
                executor.execute(r);
                logger.info("重新添加新的任务到线程池成功。。。");
            } catch (InterruptedException e) {
                logger.error("", e);
            }
        };
    }


    /*public RejectedExecutionHandler myRejectedExecutionHandler() {
        return new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                logger.info("添加新的任务到线程池失败，正在尝试重新添加。。。");
                try {
                    Thread.sleep(100);
                    executor.execute(r);
                    logger.info("重新添加新的任务到线程池成功。。。");
                } catch (InterruptedException e) {
                    logger.error("", e);
                }
            }
        };
    }*/

}
