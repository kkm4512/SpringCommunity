package com.terror.springcommunity.model.apiResponse;

import com.terror.springcommunity.constans.ApiResponseEnum;
import lombok.Getter;

@Getter
public class ApiResponse {
    private final int status;
    private final String message;

    public ApiResponse(ApiResponseEnum apiResponseEnum) {
        this.status = apiResponseEnum.getStatus().value();
        this.message = apiResponseEnum.getMessage();
    }
}
