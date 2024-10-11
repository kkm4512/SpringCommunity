package com.terror.springcommunity.exception;

import com.terror.springcommunity.constans.ApiResponseMemberEnum;

public class MemberException extends BaseException {
    public MemberException(ApiResponseMemberEnum apiResponseEnum) {
        super(apiResponseEnum);
    }
}
