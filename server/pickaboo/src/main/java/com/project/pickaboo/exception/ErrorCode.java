package com.project.pickaboo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    /* 400 Bad Request */
    EXIST_USERNAME(HttpStatus.BAD_REQUEST, "400", "이미 존재하는 아이디입니다."),
    NOT_MATCH_PASSWORD(HttpStatus.BAD_REQUEST, "400", "패스워드가 일치하지 않습니다."),
    INVALID_TOKEN(HttpStatus.BAD_REQUEST, "400", "토큰이 유효하지 않습니다."),

    /* 404 Not Found */
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "404", "존재하지 않는 사용자입니다."),

    /* 500 Server Error */
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "500", "서버가 원활하지 않습니다."),

    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

    ErrorCode(final HttpStatus status, final String code, final String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
