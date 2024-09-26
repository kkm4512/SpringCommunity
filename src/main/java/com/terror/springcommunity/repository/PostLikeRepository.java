package com.terror.springcommunity.repository;

import com.terror.springcommunity.entity.Member;
import com.terror.springcommunity.entity.Post;
import com.terror.springcommunity.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    boolean existsByMemberAndPost(Member member, Post post);
    PostLike findByMemberAndPost(Member member, Post post);
}
