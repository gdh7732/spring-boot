package com.example.model;

/**
 * @author guodahai
 * @version 2018/4/20 下午3:53
 */
public class SendRequest {

    /**
     * 交换机
     */
    private String exchange;
    /**
     * key
     */
    private String routingKey;
    /**
     * 消息内容
     */
    private Object object;

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "SendRequest{" +
                "exchange='" + exchange + '\'' +
                ", routingKey='" + routingKey + '\'' +
                ", object=" + object +
                '}';
    }
}
