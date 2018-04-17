package com.example.demo.common;

/**
 * 错误码定义
 *
 * @author guodahai
 * @version 2018/4/17 上午10:52
 */
public enum ErrorCodeEnum {

    P01("P01", "参数传入不符合规则"),
    Q01("Q01", "数据库查询失败"),
    U01("Q01", "数据库更新失败"),
    I01("Q01", "数据库插入失败"),
    D01("Q01", "数据库删除失败"),
    P99("P99", "系统异常"),
    QUA01("QUA01", "quartz异常");

    private String code;
    private String desc;

    ErrorCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
