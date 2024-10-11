package com.terror.springcommunity.exception;

import com.terror.springcommunity.constans.ApiResponsePostLikeEnum;

public class PostLikeException extends BaseException {
    public PostLikeException(ApiResponsePostLikeEnum apiResponseEnum) {
        super(apiResponseEnum);
    }
}
