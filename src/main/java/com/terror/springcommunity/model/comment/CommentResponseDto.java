package com.terror.springcommunity.model.comment;

import com.terror.springcommunity.entity.Comment;
import com.terror.springcommunity.model.TimeStampDto;
import lombok.Getter;

@Getter
public class CommentResponseDto extends TimeStampDto {
    private final Long id;
    private final String content;

    // Entity -> Dto
    public CommentResponseDto(Comment comment) {
        addDateTime(comment.getCreatedAt(), comment.getUpdatedAt());
        this.id = comment.getId();
        this.content = comment.getContent();
    }
}
