package com.terror.springcommunity.exception;

import com.terror.springcommunity.constans.response.ApiResponsePostEnum;

public class PostException extends BaseException {
    public PostException(ApiResponsePostEnum apiResponseEnum) {
        super(apiResponseEnum);
    }
}
