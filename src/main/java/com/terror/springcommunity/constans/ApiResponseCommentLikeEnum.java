package com.terror.springcommunity.constans;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ApiResponseCommentLikeEnum implements ApiResponseImpl {
    COMMENT_LIKE_SAVE_SUCCESS(HttpStatus.OK,"댓글 좋아요에 성공 하였습니다"),
    COMMENT_LIKE_DELETE_SUCCESS(HttpStatus.OK,"댓글 좋아요 삭제에 성공 하였습니다");
    

    private final HttpStatus status;
    private final String message;

    ApiResponseCommentLikeEnum(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
