package com.terror.springcommunity.constans;

import org.springframework.http.HttpStatus;

public interface ApiResponseImpl {
    HttpStatus getStatus();
    String getMessage();
}
