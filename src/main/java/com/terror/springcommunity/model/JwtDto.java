package com.terror.springcommunity.model;

import com.terror.springcommunity.constans.UserRoleEnum;
import com.terror.springcommunity.entity.Member;
import lombok.Getter;

/**
 * Jwt에 담을 내용들
 * -
 * 유저의 ID
 * 유저의 닉네임 (작성자)
 * 유저의 이메일
 * 유저의 권한
 */
@Getter
public class JwtDto {
    private final Long id;
    private final String author;
    private final String username;
    private final String email;
    private final UserRoleEnum role;

    // Member -> jwtDto
    public JwtDto(Member member) {
        this.id = member.getId();
        this.author = member.getAuthor();
        this.email = member.getEmail();
        this.role = member.getRole();
        this.username = member.getUsername();
    }
}
