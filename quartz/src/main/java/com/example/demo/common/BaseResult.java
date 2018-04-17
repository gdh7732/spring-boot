package com.example.demo.common;

import java.io.Serializable;

/**
 * @author guodahai
 * @version 2018/4/17 上午10:23
 */
public class BaseResult implements Serializable {

    private static final long serialVersionUID = 3962215109252373857L;

    private boolean success = true;
    private String errorCode;
    private String errorMessage;

    public BaseResult() {

    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
