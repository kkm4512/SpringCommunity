package com.terror.springcommunity.exception;

import com.terror.springcommunity.constans.ApiResponseAuthEnum;

public class AuthException extends BaseException {
    public AuthException(ApiResponseAuthEnum apiResponseEnum) {
        super(apiResponseEnum);
    }
}
