package com.ocean.common;

/**
 * 数据返回Result
 *
 * @author guodahai
 * @version 2018/4/17 上午11:19
 */
public class ResponseResult<T> extends BaseResult {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
