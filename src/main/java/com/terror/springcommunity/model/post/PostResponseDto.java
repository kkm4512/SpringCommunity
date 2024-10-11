package com.terror.springcommunity.model.post;

import com.terror.springcommunity.entity.Post;
import com.terror.springcommunity.model.TimeStampDto;
import com.terror.springcommunity.model.comment.CommentResponseDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PostResponseDto extends TimeStampDto {
    private Long id;
    private String title;
    private String content;
    private List<CommentResponseDto> comment;

    // Entity -> Dto
    public PostResponseDto(Post post) {
        addDateTime(post.getCreatedAt(),post.getUpdatedAt());
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.comment = post.getCommentList().stream().map(CommentResponseDto::new).toList();
    }
}
