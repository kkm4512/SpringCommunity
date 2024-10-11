package com.terror.springcommunity.model.member;

import com.terror.springcommunity.entity.Member;
import com.terror.springcommunity.model.TimeStampDto;
import lombok.Getter;

@Getter
public class MemberResponseDto extends TimeStampDto {
    private final String username;
    private final String email;
    private final String author;

    // Entity -> Dto
    public MemberResponseDto(Member member) {
        addDateTime(member.getCreatedAt(), member.getUpdatedAt());
        this.username = member.getUsername();
        this.email = member.getEmail();
        this.author = member.getAuthor();
    }
}
