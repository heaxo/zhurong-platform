package com.ao.platform.base.api;

import com.ao.platform.base.exception.BusinessException;
import com.ao.platform.base.exception.GlobalErrorCode;
import com.ao.platform.base.exception.IErrorCode;

import java.io.Serializable;

public record ApiResponse<T>(
        int code,
        String message,
        T data
) implements Serializable {

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(0, "success", data);
    }

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(0, "success", null);
    }

    public static <T> ApiResponse<T> fail(IErrorCode errorCode) {
        return new ApiResponse<>(errorCode.getCode(),
                errorCode.getMessage(),
                null);
    }

    public static <T> ApiResponse<T> fail(String message) {
        return new ApiResponse<>(GlobalErrorCode.SYSTEM_ERROR.getCode(),
                message,
                null);
    }

    public T unwrap() {

        if (code != 0) {
            throw new BusinessException(message);
        }

        return data;
    }
}
