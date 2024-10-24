package com.terror.springcommunity.exception;

import com.terror.springcommunity.constans.response.ApiResponseImpl;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {
    ApiResponseImpl apiResponseImpl;
    public BaseException(ApiResponseImpl apiResponseImpl) {
        this.apiResponseImpl = apiResponseImpl;
    }
}
