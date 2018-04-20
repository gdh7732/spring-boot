package com.example.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author guodahai
 */
public class HelloJob implements BaseJob {

    private static Logger logger = LoggerFactory.getLogger(HelloJob.class);

    public HelloJob() {

    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.warn("Hello Job执行时间: " + new Date());
    }
}  
