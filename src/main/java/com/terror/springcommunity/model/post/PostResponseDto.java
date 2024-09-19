package com.terror.springcommunity.model.post;

import com.terror.springcommunity.entity.Post;
import com.terror.springcommunity.model.TimeStampDto;
import com.terror.springcommunity.model.comment.CommentResponseDto;
import lombok.Getter;

import java.util.List;

@Getter
public class PostResponseDto extends TimeStampDto {
    private final Long id;
    private final String title;
    private final String content;
    private final List<CommentResponseDto> comment;

    // Entity -> Dto
    public PostResponseDto(Post post) {
        addDateTime(post.getCreatedAt(),post.getUpdatedAt());
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.comment = post.getCommentList().stream().map(CommentResponseDto::new).toList();
    }
}
