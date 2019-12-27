package com.ocean.common;

/**
 * 错误码定义
 *
 * @author guodahai
 * @version 2018/4/17 上午10:52
 */
public enum ErrorCodeEnum {

    P01(417, "参数传入不符合规则"),
    Q01(418, "数据库查询失败"),
    U01(418, "数据库更新失败"),
    I01(418, "数据库插入失败"),
    D01(418, "数据库删除失败"),
    P99(500, "系统异常"),
    QUA01(419, "quartz异常");

    private Integer code;
    private String desc;

    ErrorCodeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
