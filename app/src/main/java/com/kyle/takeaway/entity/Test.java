package com.kyle.takeaway.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Create by kyle on 2019/2/23
 * Function :
 */
public class Test {
    /**
     * msg : 缺少请求参数
     * code : 20001
     * data :
     */

    @SerializedName("msg")
    private String msg;
    @SerializedName("code")
    private int code;
    @SerializedName("data")
    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
