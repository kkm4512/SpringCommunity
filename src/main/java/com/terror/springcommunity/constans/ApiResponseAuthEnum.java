package com.terror.springcommunity.constans;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ApiResponseAuthEnum implements ApiResponseImpl {
    LOGIN_FAIL(HttpStatus.BAD_REQUEST,"로그인에 실패 하였습니다"),
    LOGIN_SUCCESS(HttpStatus.OK,"로그인에 성공 하였습니다");

    private final HttpStatus status;
    private final String message;

    ApiResponseAuthEnum(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
