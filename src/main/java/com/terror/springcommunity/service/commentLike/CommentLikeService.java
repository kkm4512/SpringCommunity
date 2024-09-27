package com.terror.springcommunity.service.commentLike;

import com.terror.springcommunity.model.apiResponse.comment.ApiResponseComment;

public interface CommentLikeService {
    ApiResponseComment toggleLike(Long memberId, Long commentId);
}
