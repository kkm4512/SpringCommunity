package com.terror.springcommunity.entity;

import com.terror.springcommunity.constans.response.ApiResponsePostEnum;
import com.terror.springcommunity.exception.PostException;
import com.terror.springcommunity.model.post.PostRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
@Entity
@AllArgsConstructor
@DynamicUpdate
public class Post extends TimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "post")
    private List<PostLike> postLikeList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "post")
    private List<Comment> commentList = new ArrayList<>();


    // Post -> Comment 연관관계 설정
    public void addComment(Comment comment) {
        this.commentList.add(comment);
        comment.addPost(this);
    }

    // Post -> Member 연관관계 설정
    public void addMember(Member member) {
        this.member = member;
        member.addPostList(this);
    }

    public static Post fromPostRequestDto(PostRequestDto reqDto) {
        return new Post(
                null,
                reqDto.getTitle(),
                reqDto.getContent(),
                null,
                null,
                null
        );
    }

    // 게시글 작성자가 아닌데 게시글을 수정,삭제 하려고 할시 확인
    public void isWrittenByMember(Member member){
        boolean check = this.member.equals(member);
        if (!check) throw new PostException(ApiResponsePostEnum.POST_WRITER_DIFFERENT);
    }


    public void updatePost(PostRequestDto updatePost){
        this.title = updatePost.getTitle();
        this.content = updatePost.getContent();
    }
}
