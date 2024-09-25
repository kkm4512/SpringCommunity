package com.terror.springcommunity.service.auth;

import com.terror.springcommunity.constans.ApiResponseEnum;
import com.terror.springcommunity.entity.Member;
import com.terror.springcommunity.model.apiResponse.ApiResponse;
import com.terror.springcommunity.model.member.SignUpDto;
import com.terror.springcommunity.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthRepository authRepository;

    public ApiResponse signup(SignUpDto signUpDto) {
        Member member = new Member(signUpDto);
        authRepository.save(member);
        return new ApiResponse(ApiResponseEnum.MEMBER_SAVE_SUCCESS);
    }
}
