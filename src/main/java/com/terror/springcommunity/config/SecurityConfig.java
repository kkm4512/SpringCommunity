package com.terror.springcommunity.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.terror.springcommunity.jwt.JwtManager;
import com.terror.springcommunity.security.HandlerFilterException;
import com.terror.springcommunity.security.JwtAuthenticationFilter;
import com.terror.springcommunity.security.JwtAuthorizationFilter;
import com.terror.springcommunity.security.UserDetailsServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtManager jm;
    private final AuthenticationConfiguration authenticationConfiguration;
    private final UserDetailsServiceImpl userDetailsService;
    private final ObjectMapper objectMapper;
    private final AuthenticationEntryPoint entryPoint;	// 추가


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public SecurityConfig(
            JwtManager jm,
            UserDetailsServiceImpl userDetailsService,
            AuthenticationConfiguration authenticationConfiguration,
            AuthenticationEntryPoint entryPoint,
            ObjectMapper objectMapper
    ) {
        this.jm = jm;
        this.userDetailsService = userDetailsService;
        this.authenticationConfiguration = authenticationConfiguration;
        this.objectMapper = objectMapper;
        this.entryPoint = entryPoint;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(jm,objectMapper);
        filter.setAuthenticationManager(authenticationManager(authenticationConfiguration));
        filter.setFilterProcessesUrl("/api/auths/signin");
        return filter;
    }

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() {
        return new JwtAuthorizationFilter(jm,userDetailsService) {
            @Override
            protected boolean shouldNotFilter(HttpServletRequest req) throws ServletException {
                String path = req.getRequestURI();
                // "/auth"로 시작되는 경로에 대해서는 필터가 진행되지 않음
                return path.startsWith("/api/test");
            };
        };
    }

    @Bean
    public HandlerFilterException handlerFilterException(){
        return new HandlerFilterException(objectMapper);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //CSRF 설정
        http.csrf(AbstractHttpConfigurer::disable);

        //session 방식이 아닌 jwt 방식을 사용
        http.sessionManagement(sm ->
                sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        // 요청 처리
        http.authorizeHttpRequests(authReq ->
                authReq
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/api/auths/**").permitAll() // /api/user 로시작하는 요청 모두 접근 허용 (인증 x)
                        .requestMatchers("/api/test/**").permitAll()
                        .anyRequest().authenticated() // 그 외 모든 요청 인증 처리
        );

        //필터관리
        http.exceptionHandling(handler -> handler.authenticationEntryPoint(entryPoint));	// 추가
        http.addFilterBefore(handlerFilterException(), JwtAuthenticationFilter.class);
        http.addFilterBefore(jwtAuthorizationFilter(), JwtAuthenticationFilter.class);
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
