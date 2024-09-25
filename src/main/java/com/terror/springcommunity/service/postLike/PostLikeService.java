package com.terror.springcommunity.service.postLike;

import com.terror.springcommunity.entity.Member;
import com.terror.springcommunity.entity.Post;
import com.terror.springcommunity.entity.PostLike;
import com.terror.springcommunity.model.apiResponse.ApiResponse;

public interface PostLikeService {
    ApiResponse togglePostLike(Long memberId, Long postId);
    boolean existsByMemberAndPost(Member member, Post post);
    PostLike findByMemberAndPost(Member member, Post post);
}
