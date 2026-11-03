package com.zhurong.platform.custom.exception;

import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<Void> handleValidation(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getAllErrors().stream()
                .map(error -> error instanceof FieldError fieldError
                        ? fieldError.getField() + ": " + fieldError.getDefaultMessage()
                        : error.getDefaultMessage())
                .distinct()
                .sorted()
                .collect(Collectors.joining("; "));

        if (message.isBlank()) {
            message = "参数校验失败";
        }

        return ApiResponse.fail(new BusinessException(HttpStatus.BAD_REQUEST.value(), message));
    }
}
