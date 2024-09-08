package com.terror.springcommunity;

import com.terror.springcommunity.exception.BaseException;
import com.terror.springcommunity.model.apiResponse.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(BaseException.class)
    protected ResponseEntity<ApiResponse> jwtException(BaseException e) {
        ApiResponse apiResponse = new ApiResponse(e.getApiResponseImpl());
        return ResponseEntity.status(e.getApiResponseImpl().getStatus().value()).body(apiResponse);
    }
}
