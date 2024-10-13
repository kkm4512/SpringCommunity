package com.terror.springcommunity.exception;

import com.terror.springcommunity.constans.response.ApiResponseJwtEnum;

public class JwtException extends BaseException {
    public JwtException(ApiResponseJwtEnum apiResponseEnum) {
        super(apiResponseEnum);
    }
}
