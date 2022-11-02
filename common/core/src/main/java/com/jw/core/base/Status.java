package com.jw.core.base;

/**
 * 状态码枚举类
 */
public enum Status {
    SUCCESS(0,"success"),
    FAIL(1,"fail"),
    NOT_LOGIN(550,"token已过期或不存在，请重新登录！"),
    ;

    private int status;
    private String msg;

    Status(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
