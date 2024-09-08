package com.terror.springcommunity.model.apiResponse;

import com.terror.springcommunity.constans.ApiResponseImpl;
import com.terror.springcommunity.constans.ApiResponseMemberEnum;
import lombok.Getter;

@Getter
public class ApiResponse {
    private final int status;
    private final String message;

    public ApiResponse(ApiResponseImpl apiResponse) {
        this.status = apiResponse.getStatus().value();
        this.message = apiResponse.getMessage();
    }
}
