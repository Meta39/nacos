package com.jw.common.result;

public class Res<T>{
    private Integer code;
    private String msg;
    private T data;

    public Res(){}

    public Res(T data) {
        this.code = Code.SUCCESS.getNum();
        this.msg = Code.SUCCESS.getMsg();
        this.data = data;
    }

    public static Res err(String msg) {
        Res res = new Res();
        res.setCode(Code.FAIL.getNum());
        res.setMsg(msg);
        return res;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
