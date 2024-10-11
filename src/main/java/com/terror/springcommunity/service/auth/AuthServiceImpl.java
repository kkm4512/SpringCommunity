package com.terror.springcommunity.service.auth;

import com.terror.springcommunity.constans.ApiResponseMemberEnum;
import com.terror.springcommunity.entity.Member;
import com.terror.springcommunity.exception.MemberException;
import com.terror.springcommunity.jwt.JwtManager;
import com.terror.springcommunity.model.JwtDto;
import com.terror.springcommunity.model.apiResponse.ApiResponse;
import com.terror.springcommunity.model.member.SignInDto;
import com.terror.springcommunity.model.member.SignUpDto;
import com.terror.springcommunity.repository.AuthRepository;
import com.terror.springcommunity.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthRepository authRepository;
    private final PasswordEncoder pe;
    private final JwtManager jm;
    private final MemberService memberService;
    private final RedisTemplate<String, Object> redisTemplate;

    @Transactional
    public ApiResponse signup(SignUpDto signUpDto) {
        Member member = Member.fromSignUpDto(signUpDto);
        redisSingUp(signUpDto);
        String hashedPassword = pe.encode(member.getPassword());
        member.updatePassword(hashedPassword);
        authRepository.save(member);
        return new ApiResponse(ApiResponseMemberEnum.MEMBER_SAVE_SUCCESS);
    }

    @Transactional
    public String signin(SignInDto signInDto) {
        Member member = memberService.findByMemberUsername(signInDto.getUsername());
        if (!pe.matches(signInDto.getPassword(), member.getPassword())) {
            throw new MemberException(ApiResponseMemberEnum.MEMBER_NOT_FOUND);
        }
        return jm.createJwt(new JwtDto(member));
    }

    private void redisSingUp(SignUpDto signUpDto) {
        String username = signUpDto.getUsername();
        String password = signUpDto.getPassword();
        String email = signUpDto.getEmail();
        String author = signUpDto.getAuthor();
        String role = signUpDto.getRole().toString();
        redisTemplate.opsForValue().set("username",username);
        redisTemplate.opsForValue().set("password",password);
        redisTemplate.opsForValue().set("email",email);
        redisTemplate.opsForValue().set("author",author);
        redisTemplate.opsForValue().set("role",role);
    }
}
