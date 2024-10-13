package com.terror.springcommunity.service.auth;

import com.terror.springcommunity.constans.response.ApiResponseAuthEnum;
import com.terror.springcommunity.constans.response.ApiResponseMemberEnum;
import com.terror.springcommunity.service.file.FileService;
import com.terror.springcommunity.entity.Member;
import com.terror.springcommunity.exception.AuthException;
import com.terror.springcommunity.exception.MemberException;
import com.terror.springcommunity.model.apiResponse.ApiResponse;
import com.terror.springcommunity.model.jwt.JwtDto;
import com.terror.springcommunity.model.jwt.TokenDto;
import com.terror.springcommunity.model.member.SignInDto;
import com.terror.springcommunity.model.member.SignUpDto;
import com.terror.springcommunity.repository.AuthRepository;
import com.terror.springcommunity.security.AuthUser;
import com.terror.springcommunity.security.JwtManager;
import com.terror.springcommunity.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j(topic = "AuthServiceImpl")
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthRepository authRepository;
    private final PasswordEncoder pe;
    private final JwtManager jm;
    private final MemberService memberService;
    private final RedisTemplate<String, Object> redisTemplate;
    private final FileService fileUploadService;

    @Transactional
    public ApiResponse signup(SignUpDto signUpDto, MultipartFile profile) {
        String fileName = fileUploadService.upload(
                profile,
                "profile" + "/" + signUpDto.getUsername()
        );
        String hashedPassword = pe.encode(signUpDto.getPassword());
        Member member = Member.builder()
                    .email(signUpDto.getEmail())
                    .author(signUpDto.getAuthor())
                    .role(signUpDto.getRole())
                    .password(hashedPassword)
                    .username(signUpDto.getUsername())
                    .profilePath(fileName)
                    .build();
        authRepository.save(member);
        return new ApiResponse(ApiResponseMemberEnum.MEMBER_SAVE_SUCCESS);
    }

    @Transactional
    public TokenDto signin(SignInDto signInDto) {
        Member member = memberService.findByMemberUsername(signInDto.getUsername());
        if (!pe.matches(signInDto.getPassword(), member.getPassword())) {
            throw new MemberException(ApiResponseMemberEnum.MEMBER_NOT_FOUND);
        }
        TokenDto token = TokenDto.builder()
                        .accessToken(jm.createAccessToken(new JwtDto(member)))
                        .refreshToken(jm.createRefreshToken(String.valueOf(member.getId())))
                        .build();
        redisTemplate.delete("memberId:" + member.getId());
        redisTemplate.opsForValue().set("memberId:" + member.getId(), token.getRefreshToken(),jm.REFRESH_TOKEN_EXPIRED_TIME, TimeUnit.MILLISECONDS);
        return token;
    }

    @Override
    public TokenDto regeneratedToken(String refreshToken, AuthUser authUser) {
        // 리프레쉬 토큰 유효성 검사
        jm.validateJwt(refreshToken);
        log.info("id: {}",authUser.getId());
        log.info("email: {}",authUser.getEmail());
        String redisRefreshToken = (String) redisTemplate.opsForValue().get("memberId:" + authUser.getId());
        if (!refreshToken.equals(redisRefreshToken)) {
            throw new AuthException(ApiResponseAuthEnum.NOT_EQUALS_REFRESH_TOKEN);
        }
        Member member = memberService.findByMemberId(authUser.getId());
        TokenDto token = TokenDto.builder()
                .accessToken( jm.createAccessToken(new JwtDto(member)) )
                .refreshToken( jm.createRefreshToken(String.valueOf(member.getId()) ) )
                .build();
        redisTemplate.delete("memberId:" + member.getId());
        redisTemplate.opsForValue().set("memberId:" + member.getId(), token.getRefreshToken() ,jm.REFRESH_TOKEN_EXPIRED_TIME, TimeUnit.MILLISECONDS);
        return token;
    }
}
