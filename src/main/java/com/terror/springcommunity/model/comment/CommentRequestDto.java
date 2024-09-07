package com.terror.springcommunity.model.comment;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private Long postId;
    private String content;
}
