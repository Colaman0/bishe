package com.kyle.takeaway.entity;

/**
 * Create by kyle on 2019/2/23
 * Function :
 */
public class BaseResponse<T> {
    /**
     * msg : 该手机还未注册
     * code : 10005
     * data :
     */

    private String msg;
    private int code;
    private T data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
