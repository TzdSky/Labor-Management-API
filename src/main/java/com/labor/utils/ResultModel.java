package com.labor.utils;

import com.labor.enums.ResultCode;

import java.io.Serializable;

/**
 *
 */
public class ResultModel<T> implements Serializable {

    private int code;
    private String text;
    private T data;


    public ResultModel() {
        this.code = 200;
        this.text = "success";
    }

    public ResultModel(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public ResultModel(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.text = resultCode.getMessage();
    }

    public ResultModel(T data) {
        this.code = 200;
        this.text = "success";
        this.data = data;
    }

    public ResultModel(int code, String text, T data) {
        this.code = code;
        this.text = text;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
