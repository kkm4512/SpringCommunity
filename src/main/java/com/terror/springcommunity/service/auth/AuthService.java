package com.terror.springcommunity.service.auth;

import com.terror.springcommunity.model.apiResponse.ApiResponse;
import com.terror.springcommunity.model.jwt.TokenDto;
import com.terror.springcommunity.model.member.SignInDto;
import com.terror.springcommunity.model.member.SignUpDto;
import com.terror.springcommunity.security.AuthUser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AuthService {
    ApiResponse signup(SignUpDto signUpDto, MultipartFile profile);
    TokenDto signin(SignInDto signInDto);
    TokenDto regeneratedToken(String refreshToken,AuthUser authUser);

}
