package com.zxd.pojo;


import java.io.Serializable;

public class CommonResult<T> implements Serializable {

    public static final String SUCCESS = "0";
    public static final String FALIED = "1";

    private String msg;

    private String code = CommonResult.SUCCESS;

    private String errorMsg;

    private T data;

    public CommonResult() {
    }

    public CommonResult(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }

    public CommonResult(T data) {
        this.data = data;
    }

    public CommonResult(String msg, String code, String errorMsg) {
        this.msg = msg;
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
