package com.terror.springcommunity.service.post;

import com.terror.springcommunity.entity.Member;
import com.terror.springcommunity.entity.Post;

import java.util.List;

public interface PostRepositoryQuery {
    List<Post> findAllPostsByMember(Member member);
}
