package com.project.pickaboo.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    public final ErrorCode errorCode;

    public CustomException(final ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
