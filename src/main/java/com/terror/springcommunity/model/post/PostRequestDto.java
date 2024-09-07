package com.terror.springcommunity.model.post;

import lombok.Getter;

@Getter
public class PostRequestDto {
    private Long memberId;
    private String title;
    private String content;
}
