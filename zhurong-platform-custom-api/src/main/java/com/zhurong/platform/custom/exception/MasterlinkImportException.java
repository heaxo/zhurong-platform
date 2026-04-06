package com.zhurong.platform.custom.exception;

import lombok.Getter;

@Getter
public class MasterlinkImportException extends RuntimeException {

    private final Integer code;

    public MasterlinkImportException(String message) {
        super(message);
        this.code = 500;
    }
    public MasterlinkImportException(String message, Throwable cause) {
        super(message, cause);
        this.code = 500;
    }

    public MasterlinkImportException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return super.getMessage();
    }
}
