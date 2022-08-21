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

    /**
     * 成功返回
     * @param data 数据
     */
    public Res(T data) {
        this.code = 200;
        this.msg = "成功";
        this.data = data;
    }

    /**
     * 常规500异常
     * @param msg 错误信息
     */
    public static Res err(String msg) {
        Res res = new Res();
        res.setCode(500);
        res.setMsg(msg);
        return res;
    }

    /**
     * 其它异常
     * @param code 状态码
     * @param msg 错误信息
     */
    public static Res err(Integer code,String msg) {
        Res res = new Res();
        res.setCode(code);
        res.setMsg(msg);
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
