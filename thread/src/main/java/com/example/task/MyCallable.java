package com.example.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.Callable;

/**
 * @author guodahai
 * @version 2018/4/11 下午4:18
 */
public class MyCallable implements Callable<Boolean> {

    private final Logger logger = LoggerFactory.getLogger(MyCallable.class);

    @Autowired
    private Integer i;

    public MyCallable(Integer i) {
        this.i = i;
    }

    @Override
    public Boolean call() throws Exception {
        logger.info("Task:{},thread", i);
        return true;
    }
}
