package com.terror.springcommunity.exception;

import com.terror.springcommunity.constans.ApiResponseCommentEnum;

public class CommentException extends BaseException {
    public CommentException(ApiResponseCommentEnum apiResponseEnum) {
        super(apiResponseEnum);
    }
}