package com.jw.core.base;

import java.io.Serializable;

/**
 * 统一泛型返回
 */
public class Res<T> implements Serializable {

    private static final long serialVersionUID = 531075058790517459L;

    private int code;//状态码
    private String msg;//信息
    private T data;//成功返回的数据

    public Res() {}

    public static <T> Res<T> ok(T data) {
        return returnRes( 0, "success",data);
    }

    public static <T> Res<T> err(String msg) {
        return returnRes(Status.FAIL.getStatus(),  msg,null);
    }
    public static <T> Res<T> err(int code, String msg) {
        return returnRes( code, msg,null);
    }
    private static <T> Res<T> returnRes( int code, String msg,T data) {
        Res<T> res = new Res<>();
        res.setCode(code);
        res.setMsg(msg);
        res.setData(data);
        return res;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Res)) return false;

        Res<?> res = (Res<?>) o;

        if (getCode() != res.getCode()) return false;
        if (getMsg() != null ? !getMsg().equals(res.getMsg()) : res.getMsg() != null) return false;
        return getData() != null ? getData().equals(res.getData()) : res.getData() == null;
    }

    @Override
    public int hashCode() {
        int result = getCode();
        result = 31 * result + (getMsg() != null ? getMsg().hashCode() : 0);
        result = 31 * result + (getData() != null ? getData().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Res{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
