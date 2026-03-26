package com.zhurong.platform.base.exception;

public enum GlobalErrorCode implements IErrorCode {

    SUCCESS(0, "success"),
    SYSTEM_ERROR(500, "system error"),
    PARAM_ERROR(400, "parameter error"),
    UNAUTHORIZED(401, "unauthorized"),
    FORBIDDEN(403, "forbidden");

    private final int code;
    private final String message;

    GlobalErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
