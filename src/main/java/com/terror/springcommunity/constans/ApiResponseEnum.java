package com.terror.springcommunity.constans;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ApiResponseEnum {
    MEMBER_SAVE_SUCCESS(HttpStatus.OK,"회원가입에 성공하였습니다");

    private final HttpStatus status;
    private final String message;

    ApiResponseEnum(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
