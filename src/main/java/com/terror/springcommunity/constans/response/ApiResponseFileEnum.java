package com.terror.springcommunity.constans.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ApiResponseFileEnum implements ApiResponseImpl {
    FILE_UPLOAD_SUCCESS(HttpStatus.CREATED, "파일 저장에 성공 하였습니다"),
    FILE_UPLOAD_FAIL(HttpStatus.BAD_REQUEST, "파일 저장에 실패 하였습니다"),
    FILE_URL_FAIL(HttpStatus.BAD_REQUEST, "파일을 URL로 변환하는데 실패 하였습니다");


    private final HttpStatus status;
    private final String message;

    ApiResponseFileEnum(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
