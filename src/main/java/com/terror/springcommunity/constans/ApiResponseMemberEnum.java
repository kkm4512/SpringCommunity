package com.terror.springcommunity.constans;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ApiResponseMemberEnum implements ApiResponseImpl {
    MEMBER_SAVE_SUCCESS(HttpStatus.CREATED,"회원가입에 성공하였습니다"),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND,"유저가 존재하지 않습니다"),
    MEMBER_LOGIN_SUCCESS(HttpStatus.OK,"로그인에 성공 하였습니다"),
    MEMBER_NOT_USER(HttpStatus.FORBIDDEN,"유저 권한이 아닙니다"),
    MEMBER_NOT_ADMIN(HttpStatus.FORBIDDEN,"관리자 권한이 아닙니다"),
    MEMBER_UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"인증되지 않은 사용자입니다");

    private final HttpStatus status;
    private final String message;

    ApiResponseMemberEnum(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
