package com.terror.springcommunity.entity;

import com.terror.springcommunity.constans.response.ApiResponseCommentEnum;
import com.terror.springcommunity.exception.CommentException;
import com.terror.springcommunity.model.comment.CommentRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment extends TimeStamp{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "comment")
    private List<CommentLike> commentLikeList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // Comment -> Post 연관관계 설정
    public void addPost(Post post) {
        this.post = post;
    }

    // Comment -> Member 연관관계 설정
    public void addMember(Member member) {
        this.member = member;
        member.addComment(this);
    }

    public static Comment from(CommentRequestDto reqDto) {
        return new Comment(
                null,
                reqDto.getContent(),
                null,
                null,
                null
        );
    }

    public void updateComment(CommentRequestDto reqDto) {
        this.content = reqDto.getContent();
    }

    // 댓글 작성자가 본인이 맞는지 확인
    public void isWrittenMember(Member member) {
       boolean check = this.member.equals(member);
       if (!check) throw new CommentException(ApiResponseCommentEnum.COMMENT_WRITER_DIFFERENT);
    }
}
