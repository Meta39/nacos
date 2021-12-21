package com.jw.common.result;

public enum Code {
    FAIL(0,"失败"),//失败
    SUCCESS(1,"成功"),//成功
    NOT_LOGIN(-1,"未登录"),//未登录
    ;

    private int num;
    private String msg;

    Code(){}

    Code(int num, String msg) {
        this.num = num;
        this.msg = msg;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
