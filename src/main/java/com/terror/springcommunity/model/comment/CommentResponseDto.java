package com.terror.springcommunity.model.comment;

import com.terror.springcommunity.entity.Comment;
import com.terror.springcommunity.model.TimeStampDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CommentResponseDto extends TimeStampDto {
    private Long id;
    private String content;

    // Entity -> Dto
    public CommentResponseDto(Comment comment) {
        addDateTime(comment.getCreatedAt(), comment.getUpdatedAt());
        this.id = comment.getId();
        this.content = comment.getContent();
    }
}
