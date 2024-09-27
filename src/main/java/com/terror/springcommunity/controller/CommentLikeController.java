package com.terror.springcommunity.controller;

import com.terror.springcommunity.security.UserDetailsImpl;
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

    @GetMapping("/{commentId}")
    public void toggleLike(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long commentId) {
        Long memberId = userDetails.getId();
        commentLikeService.toggleLike(memberId, commentId);
    }
}
