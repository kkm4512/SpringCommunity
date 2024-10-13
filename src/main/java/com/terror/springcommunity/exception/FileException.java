package com.terror.springcommunity.exception;

import com.terror.springcommunity.constans.response.ApiResponseFileEnum;

public class FileException extends BaseException {
    public FileException(ApiResponseFileEnum apiResponseEnum) {
        super(apiResponseEnum);
    }
}
