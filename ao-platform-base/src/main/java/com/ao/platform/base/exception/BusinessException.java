package com.ao.platform.base.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException implements IErrorCode {

    private final Integer code;

    public BusinessException(String message) {
        super(message);
        this.code = 400;
    }

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
