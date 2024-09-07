package com.terror.springcommunity.entity;

import com.terror.springcommunity.model.member.SignUpDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "member")
    private List<Post> postList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "member")
    private List<PostLike> postLikeList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "member")
    private List<CommentLike> commentLikeList = new ArrayList<>();

    // Dto -> Entity
    public Member(SignUpDto signUpDto) {
        this.username = signUpDto.getUsername();
        this.password = signUpDto.getPassword();
        this.email = signUpDto.getEmail();
        this.author = signUpDto.getAuthor();
    }
}
