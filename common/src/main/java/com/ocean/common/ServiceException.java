package com.ocean.common;

/**
 * 自定义异常
 *
 * @author guodahai
 * @version 2018/4/17 上午10:43
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = -2396422934408894887L;

    private ErrorCodeEnum errorCode;

    private String errorMsg;

    /**
     * 带错误码的构造函数
     *
     * @param errorCode
     */
    public ServiceException(ErrorCodeEnum errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.setErrorMsg(errorMsg);
    }

    public ServiceException(String message) {
        super(message);
    }

    /**
     * 带错误码的构造函数
     *
     * @param errorCode
     */
    public ServiceException(ErrorCodeEnum errorCode) {
        super(errorCode.getDesc());
        this.errorCode = errorCode;
    }

    /**
     * 获取错误码
     *
     * @return
     */
    public ErrorCodeEnum getErrorEnum() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public ErrorCodeEnum getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCodeEnum errorCode) {
        this.errorCode = errorCode;
    }


}
