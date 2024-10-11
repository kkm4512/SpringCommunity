package com.terror.springcommunity.service.commentLike;

import com.terror.springcommunity.model.apiResponse.ApiResponse;

public interface CommentLikeService {
    ApiResponse toggleLike(Long memberId, Long commentId, Long postId);
}
