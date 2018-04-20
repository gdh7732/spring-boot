package com.example.entity;

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
    private String jobGroup;
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

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }
}
