package com.jw.core.base;

import java.io.Serializable;

/**
 * 自定义运行时异常类
 */
public class Err extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 7558796578827818466L;
    private int code;//错误状态码
    private String msg;//错误信息

    public Err(){}

    /**
     * 默认普通异常
     * @param msg 错误信息
     */
    public Err(String msg){
        this.code = 500;
        this.msg = msg;
    }

    /**
     * Status配置的固定错误信息
     * @param code 状态码
     * @param msg 错误信息
     */
    public Err(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Err)) return false;

        Err err = (Err) o;

        if (getCode() != err.getCode()) return false;
        return getMsg() != null ? getMsg().equals(err.getMsg()) : err.getMsg() == null;
    }

    @Override
    public int hashCode() {
        int result = getCode();
        result = 31 * result + (getMsg() != null ? getMsg().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Err{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}