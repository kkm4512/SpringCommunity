package com.terror.springcommunity.service.member;

import com.terror.springcommunity.constans.ApiResponseMemberEnum;
import com.terror.springcommunity.entity.Member;
import com.terror.springcommunity.exception.MemberException;
import com.terror.springcommunity.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    public Member findByMemberId(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new MemberException(ApiResponseMemberEnum.MEMBER_NOT_FOUND));
    }

    public Member findByMemberUsername(String username) {
        return memberRepository.findByUsername(username).orElseThrow(() -> new MemberException(ApiResponseMemberEnum.MEMBER_NOT_FOUND));
    }
}
