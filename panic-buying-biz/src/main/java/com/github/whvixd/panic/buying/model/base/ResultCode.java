package com.github.whvixd.panic.buying.model.base;

/**
 * 响应码枚举，参考HTTP状态码的语义
 */
public enum ResultCode {
    SUCCESS(200),//成功
    BAD_REQUEST(400),//bad request
    UNAUTHORIZED(401),//未认证（签名错误）
    FORBIDDEN(403),//Forbidden
    NOT_FOUND(404),//接口不存在
    INTERNAL_SERVER_ERROR(500);//服务器内部错误

    public int code;

    ResultCode(int code) {
        this.code = code;
    }
}
