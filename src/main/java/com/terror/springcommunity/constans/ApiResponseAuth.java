package com.terror.springcommunity.constans;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ApiResponseAuth implements ApiResponseImpl {
    LOGIN_FAIL(HttpStatus.BAD_REQUEST,"로그인에 실패 하였습니다");

    private final HttpStatus status;
    private final String message;

    ApiResponseAuth(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
