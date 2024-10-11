package com.terror.springcommunity.repository;

import com.terror.springcommunity.entity.Comment;
import com.terror.springcommunity.entity.CommentLike;
import com.terror.springcommunity.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    boolean existsByCommentAndMember(Comment comment, Member member);
    CommentLike findByCommentAndMember(Comment comment, Member member);
}
