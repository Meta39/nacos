package com.jw.common.result;

public class Err extends RuntimeException{
    private Integer code;
    private String msg;

    public Err(){}

    /**
     * 默认状态码为0
     * @param message 错误信息
     */
    public Err(String message){
        this.msg = message;
    }

    /**
     * 自定义异常和状态码
     * @param code 自定义的状态码，如：登录为-1等
     * @param message 错误信息
     */
    public Err(Integer code,String message){
        this.code = code;
        this.msg = message;
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
}
