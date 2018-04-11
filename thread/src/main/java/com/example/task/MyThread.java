package com.example.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author guodahai
 * @version 2018/4/11 下午3:53
 */
public class MyThread extends Thread {

    private final Logger logger = LoggerFactory.getLogger(MyThread.class);

    private int i;

    public MyThread(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        logger.info("Task:{},thread", i);
    }
}
