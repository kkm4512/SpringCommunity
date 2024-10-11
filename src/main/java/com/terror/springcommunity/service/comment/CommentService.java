package com.terror.springcommunity.service.comment;

import com.terror.springcommunity.entity.Comment;
import com.terror.springcommunity.model.apiResponse.comment.ApiResponseComment;
import com.terror.springcommunity.model.comment.CommentRequestDto;

public interface CommentService {
    ApiResponseComment createComment(Long postId, Long memberId, CommentRequestDto reqDto);
    ApiResponseComment deleteComment(Long commentId, Long memberId);
    ApiResponseComment updateComment(Long commentId, Long memberId, CommentRequestDto reqDto);
    Comment findByCommentId(Long commentId);
}
