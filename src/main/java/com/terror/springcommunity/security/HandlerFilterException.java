package com.terror.springcommunity.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.terror.springcommunity.exception.BaseException;
import com.terror.springcommunity.model.apiResponse.ApiResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Order(1)
@Component
public class HandlerFilterException extends OncePerRequestFilter {
    private final ObjectMapper objectMapper;
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain fc) throws ServletException, IOException {
        try {
            fc.doFilter(req, res);
        } catch (BaseException e) {
            res.setStatus(e.getApiResponseImpl().getStatus().value());
            res.setContentType("application/json;charset=UTF-8");
            res.getWriter()
                    .write(objectMapper.writeValueAsString(new ApiResponse(e.getApiResponseImpl())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
