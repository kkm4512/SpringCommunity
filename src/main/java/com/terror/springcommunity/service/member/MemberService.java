package com.terror.springcommunity.service.member;

import com.terror.springcommunity.entity.Member;

public interface MemberService {
    Member findByMemberId(Long memberId);
    Member findByMemberUsername(String username);
}
