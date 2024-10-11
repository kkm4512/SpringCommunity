package com.terror.springcommunity.repository;

import com.terror.springcommunity.entity.Member;
import com.terror.springcommunity.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByMember(Member member, Pageable pageable);

    @Query("SELECT DISTINCT p FROM Post p " +
            " LEFT JOIN FETCH p.member m " +
            " LEFT JOIN FETCH p.commentList " +
            " WHERE p.member.id = :memberId")
    List<Post> findAllByMemberId(@Param("memberId") Long memberId);

    Page<Post> findAllBy(Pageable pageable);

    // QueryDsl 조회문
}


