package com.terror.springcommunity.controller;

import com.terror.springcommunity.constans.ApiResponseMemberEnum;
import com.terror.springcommunity.jwt.JwtManager;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Test API",description = "Swagger Test API")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtManager jm;

    @Operation(summary = "회원가입", description = "회원가입 하는 API")
    @Parameter(name = "SignUpDto", description = "회원가입할 유저의 정보")
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signUp(@RequestBody SignUpDto signUpDto) {
        ApiResponse apiResponse = authService.signup(signUpDto);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @PostMapping("/signin")
    public ResponseEntity<ApiResponse> signIn(@RequestBody SignInDto signInDto, HttpServletResponse res) {
        String jwt = authService.signin(signInDto);
        jm.addJwtToHeader(jwt,res);
        ApiResponse apiResponse = new ApiResponse(ApiResponseMemberEnum.MEMBER_LOGIN_SUCCESS);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }
}
