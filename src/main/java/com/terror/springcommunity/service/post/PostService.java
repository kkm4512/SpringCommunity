package com.terror.springcommunity.service.post;

import com.terror.springcommunity.entity.Post;
import com.terror.springcommunity.model.apiResponse.post.ApiResponsePost;
import com.terror.springcommunity.model.post.PostRequestDto;
import org.springframework.data.domain.Pageable;

public interface PostService {
    ApiResponsePost createPost(Long memberId, PostRequestDto postRequestDto);
    ApiResponsePost getMyPosts(Long memberId, Pageable pageable);
    ApiResponsePost getMyPostsWithOtherPosts(Pageable pageable);
    ApiResponsePost deletePost(Long memberId, Long postId);
    ApiResponsePost updatePost(Long memberId, Long postId, PostRequestDto reqDto);
    Post findByPostId(Long postId);
}
