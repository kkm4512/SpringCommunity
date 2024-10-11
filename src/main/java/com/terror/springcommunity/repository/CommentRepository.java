package com.terror.springcommunity.repository;

import com.terror.springcommunity.entity.Comment;
import com.terror.springcommunity.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
}
