package com.ocean.boot.mq.exception;

/**
 * RocketMQ自定义异常
 *
 * @author 003238
 */
public class RocketMqException extends RuntimeException {

    private static final long serialVersionUID = -3878975849119141383L;

    public RocketMqException(String msg) {
        super(msg);
    }

    public RocketMqException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public RocketMqException(Throwable cause) {
        super(cause);
    }
}
