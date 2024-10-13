package com.terror.springcommunity.constans.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ApiResponsePostLikeEnum implements ApiResponseImpl {
    POST_LIKE_SUCCESS(HttpStatus.OK," 좋아요 저장에 성공 하였습니다"),
    POST_LIKE_DELETE_SUCCESS(HttpStatus.OK," 좋아요 삭제에 성공 하였습니다"),
    POST_LIKE_GET_FAIL(HttpStatus.NOT_FOUND, "좋아요가 존재하지 않습니다");

    private final HttpStatus status;
    private final String message;

    ApiResponsePostLikeEnum(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
