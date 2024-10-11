package com.terror.springcommunity.exception;

import com.terror.springcommunity.constans.ApiResponsePostEnum;

public class PostException extends BaseException {
    public PostException(ApiResponsePostEnum apiResponseEnum) {
        super(apiResponseEnum);
    }
}
