package com.jw.common.result;

public class Res<T> {
    private int status = 1;//状态码
    private T data;//数据

    public Res(){}

    public Res(T data) {
        this.data = data;
    }

    //全局异常抛出时使用
    public static Res err(String data){
        Res res = new Res();
        res.setStatus(0);
        res.setData(data);
        return res;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
