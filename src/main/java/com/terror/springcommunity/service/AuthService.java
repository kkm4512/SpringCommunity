package com.terror.springcommunity.service;

import com.terror.springcommunity.constans.ApiResponseMemberEnum;
import com.terror.springcommunity.entity.Member;
import com.terror.springcommunity.model.apiResponse.ApiResponse;
import com.terror.springcommunity.model.member.SignUpDto;
import com.terror.springcommunity.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    private final PasswordEncoder pe;

    @Transactional
    public ApiResponse signup(SignUpDto signUpDto) {
        Member member = Member.fromSignUpDto(signUpDto);
        String hashedPassword = pe.encode(member.getPassword());
        member.updatePassword(hashedPassword);
        authRepository.save(member);
        return new ApiResponse(ApiResponseMemberEnum.MEMBER_SAVE_SUCCESS);
    }
}
