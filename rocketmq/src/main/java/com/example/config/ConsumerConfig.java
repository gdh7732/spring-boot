package com.example.config;

import java.util.List;

/**
 * @author guodahai
 * @version 2018/5/24 下午5:34
 */
public class ConsumerConfig {

    private String instanceName;

    private List<String> subscribe;

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public List<String> getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(List<String> subscribe) {
        this.subscribe = subscribe;
    }
}
