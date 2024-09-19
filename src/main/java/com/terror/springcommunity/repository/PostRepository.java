package com.terror.springcommunity.repository;

import com.terror.springcommunity.entity.Member;
import com.terror.springcommunity.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByMember(Member member, Pageable pageable);
    Page<Post> findAllBy(Pageable pageable);
}
