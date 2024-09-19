package com.terror.springcommunity.model.post;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostRequestDto {
    private String title;
    private String content;

    public static PostRequestDto of(String title, String content) {
        return new PostRequestDto(
                title,
                content
        );
    }
}
