package com.terror.springcommunity.controller;

import com.terror.springcommunity.model.apiResponse.comment.ApiResponseComment;
import com.terror.springcommunity.model.comment.CommentRequestDto;
import com.terror.springcommunity.security.AuthUser;
import com.terror.springcommunity.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j(topic = "/api/comments")
@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    // 댓글 작성
    @PostMapping("/{postId}")
    public ApiResponseComment createComment(
            @AuthenticationPrincipal AuthUser authUser,
            @PathVariable Long postId,
            @RequestBody CommentRequestDto reqDto
    ) {
        Long memberId = authUser.getId();
        return commentService.createComment(postId,memberId,reqDto);
    }
    // 댓글 삭제, 본인만 삭제 가능하게 해야함
    @DeleteMapping("/{commentId}")
    public ApiResponseComment deleteComment(
            @AuthenticationPrincipal AuthUser authUser,
            @PathVariable Long commentId
    ) {
        Long memberId = authUser.getId();
        return commentService.deleteComment(commentId,memberId);
    }

    // 댓글 수정, 본인만 수정 가능하게 해야함
    @PutMapping("/{commentId}")
    public ApiResponseComment updateComment(
            @AuthenticationPrincipal AuthUser authUser,
            @PathVariable Long commentId,
            @RequestBody CommentRequestDto reqDto
    ) {
        Long memberId = authUser.getId();
        return commentService.updateComment(commentId,memberId,reqDto);
    }
}
