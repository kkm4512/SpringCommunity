package com.terror.springcommunity.entity;

import com.terror.springcommunity.constans.response.UserRoleEnum;
import com.terror.springcommunity.model.member.SignUpDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Entity
@NoArgsConstructor
@Builder
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

    private String profilePath;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "member")
    private final List<Post> postList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "member")
    private final List<Comment> commentList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "member")
    private final List<PostLike> postLikeList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "member")
    private final List<CommentLike> commentLikeList = new ArrayList<>();



    public void addComment(Comment comment){
        this.commentList.add(comment);
    }

    public void addPostList(Post post) {
        this.postList.add(post);
    }

    public Member(Long id, String username, String password, String email, String author, UserRoleEnum role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.author = author;
        this.role = role;
    }

    public Member(Long id, String username, String password, String email, String author, UserRoleEnum role, String profilePath) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.author = author;
        this.role = role;
        this.profilePath = profilePath;
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

    public static Member fromSignUpDto(SignUpDto signUpDto, String profilePath){
        return new Member(
                null,
                signUpDto.getUsername(),
                signUpDto.getPassword(),
                signUpDto.getEmail(),
                signUpDto.getAuthor(),
                signUpDto.getRole(),
                profilePath
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
