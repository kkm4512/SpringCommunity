package com.terror.springcommunity.service.auth;

import com.terror.springcommunity.model.apiResponse.ApiResponse;
import com.terror.springcommunity.model.member.SignInDto;
import com.terror.springcommunity.model.member.SignUpDto;

public interface AuthService {
    ApiResponse signup(SignUpDto signUpDto);
    String signin(SignInDto signInDto);

}
