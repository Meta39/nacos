package com.jw.core.base;

/**
 * 状态码枚举类
 */
public enum Status {
    NOT_LOGIN(550,"token已过期或不存在，请重新登录！"),
    ;

    private int status;
    private String error;

    Status(int status, String error) {
        this.status = status;
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
