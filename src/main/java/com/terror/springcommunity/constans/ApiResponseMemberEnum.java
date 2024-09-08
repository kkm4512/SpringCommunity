package com.terror.springcommunity.constans;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ApiResponseMemberEnum implements ApiResponseImpl {
    MEMBER_SAVE_SUCCESS(HttpStatus.OK,"회원가입에 성공하였습니다"),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND,"유저가 존재하지 않습니다");

    private final HttpStatus status;
    private final String message;

    ApiResponseMemberEnum(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
