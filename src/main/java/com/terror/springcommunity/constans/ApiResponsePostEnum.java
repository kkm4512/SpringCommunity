package com.terror.springcommunity.constans;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ApiResponsePostEnum implements ApiResponseImpl {
    POST_SAVE_SUCCESS(HttpStatus.OK,"게시글 저장에 성공 하였습니다"),
    POST_SAVE_FAIL(HttpStatus.OK,"게시글 저장에 실패 하였습니다"),
    POST_UPDATE_SUCCESS(HttpStatus.OK,"게시글 수정에 성공 하였습니다"),
    POST_UPDATE_FAIL(HttpStatus.OK,"게시글 수정에 실패 하였습니다"),
    POST_DELETE_SUCCESS(HttpStatus.OK,"게시글 삭제에 성공 하였습니다"),
    POST_DELETE_FAIL(HttpStatus.OK,"게시글 삭제에 실패 하였습니다");

    private final HttpStatus status;
    private final String message;

    ApiResponsePostEnum(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
