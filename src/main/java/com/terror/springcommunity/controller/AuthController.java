package com.terror.springcommunity.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.terror.springcommunity.constans.response.ApiResponseMemberEnum;
import com.terror.springcommunity.model.jwt.TokenDto;
import com.terror.springcommunity.security.AuthUser;
import com.terror.springcommunity.security.JwtManager;
import com.terror.springcommunity.model.apiResponse.ApiResponse;
import com.terror.springcommunity.model.member.SignInDto;
import com.terror.springcommunity.model.member.SignUpDto;
import com.terror.springcommunity.service.auth.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "Test API",description = "Swagger Test API")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtManager jm;
    private final ObjectMapper objectMapper;

    @Operation(summary = "회원가입", description = "회원가입 하는 API")
    @Parameter(name = "SignUpDto", description = "회원가입할 유저의 정보")
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signUp(
            @RequestPart(value = "signUpDto") String StringSignUpDto,
            @RequestPart(value = "profile", required = false) MultipartFile profile) throws JsonProcessingException {
        SignUpDto signUpDto = objectMapper.readValue(StringSignUpDto, SignUpDto.class);
        ApiResponse apiResponse = authService.signup(signUpDto,profile);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @PostMapping("/signin")
    public ResponseEntity<ApiResponse> signIn(@RequestBody SignInDto signInDto, HttpServletResponse res) {
        TokenDto token = authService.signin(signInDto);
        jm.addAccessTokenHeader(token.getAccessToken(),res);
        jm.addRefreshTokenHeader(token.getRefreshToken(),res);
        ApiResponse apiResponse = new ApiResponse(ApiResponseMemberEnum.MEMBER_LOGIN_SUCCESS);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @GetMapping("/refresh-token")
    public ResponseEntity<ApiResponse> regeneratedToken(@AuthenticationPrincipal AuthUser authUser, HttpServletResponse res, HttpServletRequest req) {
        String refreshToken = jm.getRefreshTokenHeader(req);
        TokenDto token = authService.regeneratedToken(refreshToken,authUser);
        jm.addAccessTokenHeader(token.getAccessToken(),res);
        jm.addRefreshTokenHeader(token.getRefreshToken(),res);
        ApiResponse apiResponse = new ApiResponse(ApiResponseMemberEnum.MEMBER_LOGIN_SUCCESS);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }
}
