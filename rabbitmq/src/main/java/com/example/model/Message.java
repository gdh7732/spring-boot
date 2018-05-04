package com.example.model;

/**
 * @author guodahai
 * @version 2018/4/20 下午3:53
 */
public class Message {

    /**
     * 交换机
     */
    private String exchange;
    /**
     * key
     */
    private String routeKey;
    /**
     * 消息内容
     */
    private Object msg;

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getRouteKey() {
        return routeKey;
    }

    public void setRouteKey(String routeKey) {
        this.routeKey = routeKey;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Message{" +
                "exchange='" + exchange + '\'' +
                ", routeKey='" + routeKey + '\'' +
                ", msg=" + msg +
                '}';
    }
}
