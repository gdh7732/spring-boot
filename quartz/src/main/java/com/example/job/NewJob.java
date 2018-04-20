package com.example.job;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author guodahai
 */
public class NewJob implements BaseJob {

    private static Logger logger = LoggerFactory.getLogger(NewJob.class);

    public NewJob() {

    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.warn("New Job执行时间: " + new Date());
    }
}  