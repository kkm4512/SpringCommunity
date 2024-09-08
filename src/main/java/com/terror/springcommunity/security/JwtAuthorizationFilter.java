package com.terror.springcommunity.security;

import com.terror.springcommunity.jwt.JwtManager;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Order(2)
@Slf4j(topic = "JwtAuthorizationFilter")
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private final JwtManager jm;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain fc) throws ServletException, IOException {
        String jwt = jm.getJwtFromHeader(req);
        if (StringUtils.hasText(jwt)) {
            jm.validateJwt(jwt);
            Claims claims = jm.getUserInfoFromJwt(jwt);
            setAuthentication(claims.get("username", String.class));
        }
        fc.doFilter(req, res);
    }

    // 인증 처리
    private void setAuthentication(String username) {
        SecurityContext sc = SecurityContextHolder.createEmptyContext();
        Authentication auth = createAuthentication(username);
        sc.setAuthentication(auth);
        SecurityContextHolder.setContext(sc);
    }

    //인증객체 생성
    private Authentication createAuthentication(String username) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        log.info(userDetails.getUsername());
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}


