package com.example.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author guodahai
 * @version 2018/4/11 下午4:12
 */
public class MyRunnable implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(MyRunnable.class);

    private int i;

    public MyRunnable(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        logger.info("Task:{},thread", i);
    }

}
