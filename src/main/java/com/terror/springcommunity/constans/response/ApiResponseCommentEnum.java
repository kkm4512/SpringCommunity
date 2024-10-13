package com.terror.springcommunity.constans.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ApiResponseCommentEnum implements ApiResponseImpl {
    COMMENT_SAVE_SUCCESS(HttpStatus.OK,"댓글 저장에 성공 하였습니다"),
    COMMENT_SAVE_FAIL(HttpStatus.BAD_REQUEST,"댓글 저장에 실패 하였습니다"),
    COMMENT_UPDATE_SUCCESS(HttpStatus.OK,"댓글 수정에 성공 하였습니다"),
    COMMENT_UPDATE_FAIL(HttpStatus.BAD_REQUEST,"댓글 수정에 실패 하였습니다"),
    COMMENT_DELETE_SUCCESS(HttpStatus.OK,"댓글 삭제에 성공 하였습니다"),
    COMMENT_DELETE_FAIL(HttpStatus.BAD_REQUEST,"댓글 삭제에 실패 하였습니다"),
    COMMENT_GET_SUCCESS(HttpStatus.OK,"댓글 조회에 성공 하였습니다"),
    COMMENT_GET_FAIL(HttpStatus.NOT_FOUND,"댓글 조회에 실패 하였습니다"),
    COMMENT_WRITER_DIFFERENT(HttpStatus.BAD_REQUEST,"댓글 작성자는, 본인이 아닙니다");

    private final HttpStatus status;
    private final String message;

    ApiResponseCommentEnum(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
