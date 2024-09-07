package com.terror.springcommunity.controller;

import com.terror.springcommunity.model.apiResponse.ApiResponse;
import com.terror.springcommunity.model.member.SignUpDto;
import com.terror.springcommunity.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auths")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signup(@RequestBody SignUpDto signUpDto) {
        ApiResponse result = authService.signup(signUpDto);
        return ResponseEntity.status(result.getStatus()).body(result);
    }
}
