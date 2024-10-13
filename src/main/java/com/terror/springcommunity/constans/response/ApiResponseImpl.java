package com.terror.springcommunity.constans.response;

import org.springframework.http.HttpStatus;

public interface ApiResponseImpl {
    HttpStatus getStatus();
    String getMessage();
}
