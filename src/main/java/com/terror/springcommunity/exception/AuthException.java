package com.terror.springcommunity.exception;

import com.terror.springcommunity.constans.ApiResponseAuth;

public class AuthException extends BaseException {
    public AuthException(ApiResponseAuth apiResponseEnum) {
        super(apiResponseEnum);
    }
}
