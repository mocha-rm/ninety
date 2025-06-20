package com.jhlab.ninety.global.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class GlobalException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String message;

    public GlobalException(ExceptionType exceptionType) {
        super();
        this.httpStatus = exceptionType.getHttpStatus();
        this.errorCode = exceptionType.getErrorCode();
        this.message = exceptionType.getMessage();
    }
}
