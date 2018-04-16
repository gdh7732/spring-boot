package com.example.demo.entity;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * @author guodahai
 */
@Entity
@Table(name = "job_trigger")
public class JobAndTrigger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 任务名称
     */
    @Column(name = "job_name")
    private String jobName;
    /**
     * 任务所在组
     */
    @Column(name = "job_group")
    private String jobGroup;
    /**
     * 任务类名
     */
    @Column(name = "job_classname")
    private String jobClassName;
    /**
     * 触发器名称
     */
    @Column(name = "trigger_name")
    private String triggerName;
    /**
     * 触发器所在组
     */
    @Column(name = "trigger_group")
    private String triggerGroup;
    /**
     *
     */
    @Column(name = "repeat_interval")
    private BigInteger repeatInterval;
    /**
     *
     */
    @Column(name = "times_triggered")
    private BigInteger timesTriggered;
    /**
     * 表达式
     */
    @Column(name = "cron_expression")
    private String cronExpression;
    /**
     * 时区
     */
    @Column(name = "time_zone_id")
    private String timeZoneId;

    public JobAndTrigger() {
    }

    public JobAndTrigger(String jobName, String jobGroup, String jobClassName, String triggerName, String triggerGroup) {
        this.jobName = jobName;
        this.jobGroup = jobGroup;
        this.jobClassName = jobClassName;
        this.triggerName = triggerName;
        this.triggerGroup = triggerGroup;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getJobClassName() {
        return jobClassName;
    }

    public void setJobClassName(String jobClassName) {
        this.jobClassName = jobClassName;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    public String getTriggerGroup() {
        return triggerGroup;
    }

    public void setTriggerGroup(String triggerGroup) {
        this.triggerGroup = triggerGroup;
    }

    public BigInteger getRepeatInterval() {
        return repeatInterval;
    }

    public void setRepeatInterval(BigInteger repeatInterval) {
        this.repeatInterval = repeatInterval;
    }

    public BigInteger getTimesTriggered() {
        return timesTriggered;
    }

    public void setTimesTriggered(BigInteger timesTriggered) {
        this.timesTriggered = timesTriggered;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getTimeZoneId() {
        return timeZoneId;
    }

    public void setTimeZoneId(String timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

    @Override
    public String toString() {
        return "JobAndTrigger{" +
                "id=" + id +
                ", jobName='" + jobName + '\'' +
                ", jobGroup='" + jobGroup + '\'' +
                ", jobClassName='" + jobClassName + '\'' +
                ", triggerName='" + triggerName + '\'' +
                ", triggerGroup='" + triggerGroup + '\'' +
                ", repeatInterval=" + repeatInterval +
                ", timesTriggered=" + timesTriggered +
                ", cronExpression='" + cronExpression + '\'' +
                ", timeZoneId='" + timeZoneId + '\'' +
                '}';
    }
}
