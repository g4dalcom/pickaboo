package com.project.pickaboo.exception;

import org.springframework.http.HttpStatus;
public record ErrorResponse(HttpStatus status, String code, String message) {
    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(errorCode.getStatus(), errorCode.getCode(), errorCode.getMessage());
    }
}
