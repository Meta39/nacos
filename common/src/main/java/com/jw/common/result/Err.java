package com.jw.common.result;

public class Err extends RuntimeException{
    private int status;//状态码
    private String data;//错误信息

    public Err(){}
    public Err(String message){
        this.data = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
