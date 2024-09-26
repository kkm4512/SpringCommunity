package com.terror.springcommunity.entity;

import com.terror.springcommunity.constans.UserRoleEnum;
import com.terror.springcommunity.model.member.SignUpDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Member extends TimeStamp {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, columnDefinition = "CHAR(60)")
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "member")
    private final List<Post> postList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "member")
    private final List<Comment> commentList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "member")
    private final List<PostLike> postLikeList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "member")
    private final List<CommentLike> commentLikeList = new ArrayList<>();


    public void updatePassword(String password) {
        this.password = password;
    }

    public void addComment(Comment comment){
        this.commentList.add(comment);
    }

    public void addPostList(Post post) {
        this.postList.add(post);
    }

    public static Member fromSignUpDto(SignUpDto signUpDto){
        return new Member(
                null,
                signUpDto.getUsername(),
                signUpDto.getPassword(),
                signUpDto.getEmail(),
                signUpDto.getAuthor(),
                signUpDto.getRole()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member member)) return false;
        return Objects.equals(id, member.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
