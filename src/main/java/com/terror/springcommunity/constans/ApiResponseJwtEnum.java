package com.terror.springcommunity.constans;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ApiResponseJwtEnum implements ApiResponseImpl {
    JWT_ADD_HEADER_FAIL(HttpStatus.BAD_REQUEST,"Jwt를 헤더에 추가하는데 실패 하였습니다"),
    JWT_PARSING_FAIL(HttpStatus.BAD_REQUEST,"Jwt를 자르는데 실패하였습니다"),
    JWT_EXPIRED(HttpStatus.UNAUTHORIZED,"Jwt의 만료기한이 지났습니다"),
    JWT_UNSUPPORTED(HttpStatus.UNAUTHORIZED,"jwt의 형식이 지원되지 않습니다"),
    JWT_MALFORMED(HttpStatus.UNAUTHORIZED,"jwt가 손상되었습니다"),
    JWT_NOT_SIGNATURE(HttpStatus.UNAUTHORIZED,"jwt의 시그니처 검증에 실패 하였습니다"),
    JWT_GET_USER_FAIL(HttpStatus.BAD_REQUEST,"jwt로부터 유저의 정보를 가져오는데 실패 하였습니다"),
    JWT_GET_HEADER_FAIL(HttpStatus.BAD_REQUEST,"jwt를 헤더로 부터 추출하는데 실패 하였습니다");


    private final HttpStatus status;
    private final String message;

    ApiResponseJwtEnum(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
