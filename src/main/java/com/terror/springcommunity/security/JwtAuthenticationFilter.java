package com.terror.springcommunity.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.terror.springcommunity.constans.ApiResponseAuthEnum;
import com.terror.springcommunity.exception.AuthException;
import com.terror.springcommunity.jwt.JwtManager;
import com.terror.springcommunity.model.JwtDto;
import com.terror.springcommunity.model.apiResponse.ApiResponse;
import com.terror.springcommunity.model.member.SignInDto;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final JwtManager jm;
    private final ObjectMapper objectMapper;

    public JwtAuthenticationFilter(JwtManager jm, ObjectMapper objectMapper) {
        this.jm = jm;
        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        try {
            SignInDto reqDto = objectMapper.readValue(req.getInputStream(), SignInDto.class);
            // 사용자 정보를 기반으로 인증 토큰 생성
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            reqDto.getUsername(),
                            reqDto.getPassword(),
                            null
                    )
            );
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
    //로그인 성공
    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UserDetailsImpl member = (UserDetailsImpl) authResult.getPrincipal();
        JwtDto jwtDto = new JwtDto(member.getMember());
        String jwt = jm.createJwt(jwtDto);
        jm.addJwtToHeader(jwt, res);
        ApiResponse apiResponse = new ApiResponse(ApiResponseAuthEnum.LOGIN_SUCCESS);
        res.setStatus(apiResponse.getStatus());
        res.setContentType("application/json;charset=UTF-8");
        res.getWriter().write(objectMapper.writeValueAsString(apiResponse));
    }

    //로그인 실패
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest req, HttpServletResponse res, AuthenticationException failed) throws IOException, ServletException {
        throw new AuthException(ApiResponseAuthEnum.LOGIN_FAIL);
    }
}
