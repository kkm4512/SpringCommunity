package com.terror.springcommunity.jwt;

import com.terror.springcommunity.constans.ApiResponseJwtEnum;
import com.terror.springcommunity.exception.JwtException;
import com.terror.springcommunity.model.JwtDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Slf4j(topic = "JwtManger")
@Component
public class JwtManager {

    // HeaderKey 값
    public static final String AUTHORIZATION_HEADER = "Authorization";
    // Token 식별자
    public static final String BEARER = "Bearer ";
    // 사용자 권한 값의 key
    public static final String AUTHORIZATION_KEY = "auth";
    // jwt 만료기한
    @Value("${jwt.expired_time}")
    public Long EXPIRES_TOKEN;
    // jwt secretKey
    @Value("${jwt.secret_key}")
    private String JWT_SECRET_KEY;
    // 사용할 알고리즘
    private static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    // JWT 를생성,검증할때 사용되는 암호화 키
    private static Key key;

    //PostConstruct 로인해, JwtUtil 의 모든 인스턴스가 생성되고, 모든 의존성이 주입된 맨 마지막에 호출됨
    @PostConstruct
    public void init() {
        byte[] bytes = Base64.getDecoder().decode(JWT_SECRET_KEY);
        key = Keys.hmacShaKeyFor(bytes);
    }

    //Jwt 생성
    public String createJwt(JwtDto jwtDto) {
        Date date = new Date();
        return BEARER +
                Jwts.builder()
                        .setSubject(String.valueOf(jwtDto.getId())) //사용자 식별값 (ID)
                        .claim("author", jwtDto.getAuthor()) // 작성자 설정
                        .claim("role", jwtDto.getRole())
                        .claim("username", jwtDto.getUsername()) // 이름 설정
                        .claim("email", jwtDto.getEmail()) // 이메일 설정
                        .claim(AUTHORIZATION_KEY, jwtDto.getRole()) // 권한 설정
                        .setExpiration(new Date(date.getTime() + EXPIRES_TOKEN)) // 만료 시간
                        .setIssuedAt(date) // 발급일
                        .signWith(key, signatureAlgorithm) // 암호화 알고리즘
                        .compact(); // 최종적인 jwt 생성
    }

    // Jwt Header 에 저장
    public void addJwtToHeader(String jwt, HttpServletResponse res) {
        try {
            res.addHeader(AUTHORIZATION_HEADER, jwt); // 응답 객체에 우리가 만든 Name-Value 쿠키값 추가
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new JwtException(ApiResponseJwtEnum.JWT_ADD_HEADER_FAIL);
        }
    }

    // Jwt 토큰 잘라주기
    public String substringJwt(String jwt) {
        try {
            return jwt.substring(7);
        } catch (Exception e) {
            throw new JwtException(ApiResponseJwtEnum.JWT_PARSING_FAIL);
        }
    }

    // jwt 검증
    public void validateJwt(String jwt) {
        try {
            log.info("Jwt 유효 검증 시작");
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt);
        } catch (SignatureException e) {
            log.error(e.getMessage());
            throw new JwtException(ApiResponseJwtEnum.JWT_NOT_SIGNATURE);
        } catch (ExpiredJwtException e) {
            log.error(e.getMessage());
            throw new JwtException(ApiResponseJwtEnum.JWT_EXPIRED);
        } catch (UnsupportedJwtException e) {
            log.error(e.getMessage());
            throw new JwtException(ApiResponseJwtEnum.JWT_UNSUPPORTED);
        } catch (MalformedJwtException e) {
            log.error(e.getMessage());
            throw new JwtException(ApiResponseJwtEnum.JWT_MALFORMED);
        }
    }

    // Jwt 에서 사용자 정보 추출
    public Claims getUserInfoFromJwt(String jwt) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
        } catch (Exception e) {
            throw new JwtException(ApiResponseJwtEnum.JWT_GET_USER_FAIL);
        }
    }

    // Header 에서 Jwt 가져온후, "Beaeer " 값 제거후 반환
    public String getJwtFromHeader(HttpServletRequest request) {
        try {
            if (StringUtils.hasText(request.getHeader(AUTHORIZATION_HEADER))) {
                return substringJwt(request.getHeader(AUTHORIZATION_HEADER));
            } return null;
        } catch (JwtException e) {
            log.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            throw new JwtException(ApiResponseJwtEnum.JWT_GET_HEADER_FAIL);
        }
    }

    // Header 에서 Jwt 가져온후 그댜로 반환
    public String getJwtFromHeaderOrigin(HttpServletRequest request) {
        try {
            return request.getHeader(AUTHORIZATION_HEADER);
        } catch (Exception e) {
            throw new JwtException(ApiResponseJwtEnum.JWT_GET_HEADER_FAIL);
        }
    }
}
