package com.heliang.enums;

public enum ResultInfo {

    // HTTP状态码 200 OK
    SUCCESS(20000, "请求成功"),

    // HTTP状态码 400 Bad Request
    REQUEST_ERROR(54000, "解析数据错误、HTTP协议存在问题"),
    PARAM_ERROR(54001, "请求参数错误"),
    USER_EXIST_ERROR(54002, "用户已存在"),

    // HTTP状态码 401 Unauthorized
    UNAUTHORIZED_ERROR(54010, "认证非法"),
    BAD_CREDENTIALS(54011, "用户名或密码错误"),

    // HTTP状态码 403 Forbidden
    FORBIDDEN_ERROR(54030, "用户无权限"),

    // HTTP状态码 422 Unprocessable Entity
    UNPROCESSABLE_ENTITY_ERROR(54220, "校验错误"),

    // HTTP状态码 429 Too Many Requests
    TOO_MANY_REQUEST_ERROR(54290, "请求频次达到上限"),

    // HTTP状态码 500 Internal Server Error
    INTERNAL_SERVER_ERROR(55000, "服务器内部异常");

    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 消息
     */
    private final String message;

    ResultInfo(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
