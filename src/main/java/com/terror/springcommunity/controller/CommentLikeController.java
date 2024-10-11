package com.terror.springcommunity.controller;

import com.terror.springcommunity.model.apiResponse.ApiResponse;
import com.terror.springcommunity.security.AuthUser;
import com.terror.springcommunity.service.commentLike.CommentLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comments/likes")
@RequiredArgsConstructor
public class CommentLikeController {
    private final CommentLikeService commentLikeService;

    @GetMapping("/{postId}/{commentId}")
    public ApiResponse toggleLike(@AuthenticationPrincipal AuthUser authUser, @PathVariable Long commentId, @PathVariable Long postId) {
        Long memberId = authUser.getId();
        return commentLikeService.toggleLike(memberId, commentId, postId);
    }
}
