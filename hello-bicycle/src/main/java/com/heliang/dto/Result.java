package com.heliang.dto;

import com.heliang.enums.ResultInfo;

import java.io.Serializable;

/**
 * 统一返回格式
 */
public class Result implements Serializable {

    private Integer code;

    private String message;

    private Object data;

    public Result(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result success() {
        return new Result(ResultInfo.SUCCESS.getCode(), ResultInfo.SUCCESS.getMessage(), null);
    }

    public static Result success(Object data) {
        return new Result(ResultInfo.SUCCESS.getCode(), ResultInfo.SUCCESS.getMessage(), data);
    }

    public static Result failure() {
        return new Result(ResultInfo.INTERNAL_SERVER_ERROR.getCode(), ResultInfo.INTERNAL_SERVER_ERROR.getMessage(), null);
    }

    public static Result failure(ResultInfo resultInfo) {
        return new Result(resultInfo.getCode(), resultInfo.getMessage(), null);
    }

    public static Result failure(ResultInfo resultInfo, Object data) {
        return new Result(resultInfo.getCode(), resultInfo.getMessage(), data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
