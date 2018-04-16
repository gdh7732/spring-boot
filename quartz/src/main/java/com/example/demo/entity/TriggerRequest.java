package com.example.demo.entity;

/**
 * @author guodahai
 * @version 2018/4/16 下午5:37
 */
public class TriggerRequest {
    /**
     * job类名
     */
    private String jobClassName;
    /**
     * job所在组
     */
    private String jobGroupName;
    /**
     * 表达式
     */
    private String cronExpression;

    public String getJobClassName() {
        return jobClassName;
    }

    public void setJobClassName(String jobClassName) {
        this.jobClassName = jobClassName;
    }

    public String getJobGroupName() {
        return jobGroupName;
    }

    public void setJobGroupName(String jobGroupName) {
        this.jobGroupName = jobGroupName;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }
}
