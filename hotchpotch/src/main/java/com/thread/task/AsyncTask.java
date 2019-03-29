package com.thread.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


/**
 * @author guodahai
 * @version 2018/4/11 下午2:24
 */
@Component
public class AsyncTask {

    private final Logger logger = LoggerFactory.getLogger(AsyncTask.class);

    @Async("myTaskAsyncPool")
    public void task(int i) {
        logger.info("Task:{},thread", i);
    }
}
