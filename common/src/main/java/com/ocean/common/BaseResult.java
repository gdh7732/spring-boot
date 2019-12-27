package com.ocean.common;

import java.io.Serializable;

/**
 * @author guodahai
 * @version 2018/4/17 上午10:23
 */
public class BaseResult implements Serializable {

    private static final long serialVersionUID = 3962215109252373857L;

    private boolean success = true;
    private Integer errorCode = 200;
    private String errorMessage;

    public BaseResult() {

    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
