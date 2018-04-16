package com.example.demo.service.impl;

import com.example.demo.entity.TriggerRequest;
import com.example.demo.job.BaseJob;
import com.example.demo.service.SchedulerService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author guodahai
 * @version 2018/4/16 下午5:25
 */
@Service
public class SchedulerServiceImpl implements SchedulerService {

    @Autowired
    @Qualifier("scheduler")
    private Scheduler scheduler;

    @Override
    public void add(TriggerRequest request) throws Exception {
        String jobClassName = request.getJobClassName();
        String jobGroupName = request.getJobGroupName();
        String cronExpression = request.getCronExpression();

        // 启动调度器
        scheduler.start();

        //构建job信息
        JobDetail jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass()).withIdentity(jobClassName, jobGroupName).build();

        //表达式调度构建器(即任务执行的时间)
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

        //按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName, jobGroupName)
                .withSchedule(scheduleBuilder).build();

        try {
            scheduler.scheduleJob(jobDetail, trigger);

        } catch (SchedulerException e) {
            System.out.println("创建定时任务失败" + e);
            throw new Exception("创建定时任务失败");
        }
    }

    @Override
    public void pause(TriggerRequest request) throws Exception {
        String jobClassName = request.getJobClassName();
        String jobGroupName = request.getJobGroupName();
        scheduler.pauseJob(JobKey.jobKey(jobClassName, jobGroupName));
    }

    @Override
    public void resume(TriggerRequest request) throws Exception {
        String jobClassName = request.getJobClassName();
        String jobGroupName = request.getJobGroupName();
        scheduler.resumeJob(JobKey.jobKey(jobClassName, jobGroupName));
    }

    @Override
    public void reschedule(TriggerRequest request) throws Exception {
        String jobClassName = request.getJobClassName();
        String jobGroupName = request.getJobGroupName();
        String cronExpression = request.getCronExpression();
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            System.out.println("更新定时任务失败" + e);
            throw new Exception("更新定时任务失败");
        }
    }

    @Override
    public void delete(TriggerRequest request) throws Exception {
        String jobClassName = request.getJobClassName();
        String jobGroupName = request.getJobGroupName();
        scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName, jobGroupName));
        scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName, jobGroupName));
        scheduler.deleteJob(JobKey.jobKey(jobClassName, jobGroupName));
    }

    public static BaseJob getClass(String classname) throws Exception {
        Class<?> class1 = Class.forName(classname);
        return (BaseJob) class1.newInstance();
    }
}
