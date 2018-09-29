package com.example.thread;

import com.example.excute.TaskExecutePool;
import com.example.task.AsyncTask;
import com.example.task.MyCallable;
import com.example.task.MyRunnable;
import com.example.task.MyThread;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ThreadApplicationTests {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AsyncTask asyncTask;

    @Autowired
    private TaskExecutePool taskExecutePool;


    @Test
    public void testAsyncTask() {
        for (int i = 0; i < 100; i++) {
            asyncTask.task(i);
        }
        logger.info("All tasks finished.");
    }

    @Test
    public void testMyThread() {
        for (int i = 0; i < 100; i++) {
            ThreadPoolTaskExecutor executor = taskExecutePool.myTaskAsyncPool();
            executor.execute(new MyThread(i));
        }
        logger.info("All tasks finished.");
    }

    @Test
    public void testMyRunnable() {
        for (int i = 0; i < 100; i++) {
            ThreadPoolTaskExecutor executor = taskExecutePool.myTaskAsyncPool();
            executor.execute(new MyRunnable(i));
        }
        logger.info("All tasks finished.");
    }

    @Test
    public void testMyCallable() throws ExecutionException, InterruptedException {
        for (int i = 0; i < 100; i++) {
            ThreadPoolTaskExecutor executor = taskExecutePool.myTaskAsyncPool();
            Future<Boolean> future = executor.submit(new MyCallable(i));
                Boolean b = future.get();
                logger.info("{}",b);
        }
        logger.info("All tasks finished.");
    }



}
