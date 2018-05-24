package com.example.config;

/**
 * @author guodahai
 * @version 2018/5/24 下午5:33
 */
public class ProducerConfig {

    private String instanceName;

    private String tranInstanceName;

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public String getTranInstanceName() {
        return tranInstanceName;
    }

    public void setTranInstanceName(String tranInstanceName) {
        this.tranInstanceName = tranInstanceName;
    }
}
