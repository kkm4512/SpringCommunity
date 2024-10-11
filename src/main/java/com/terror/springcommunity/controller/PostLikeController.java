package com.terror.springcommunity.controller;

import com.terror.springcommunity.model.apiResponse.ApiResponse;
import com.terror.springcommunity.security.AuthUser;
import com.terror.springcommunity.service.postLike.PostLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j(topic = "/api/posts/likes")
@RestController
@RequestMapping("/api/posts/likes")
@RequiredArgsConstructor
public class PostLikeController {
    private final PostLikeService postLikeService;

    // 사용자가 좋아요를 눌렀는데, 한번더 누르면 취소 시키기 (삭제)
    @GetMapping("/{postId}")
    public ApiResponse togglePostLike(@AuthenticationPrincipal AuthUser authUser, @PathVariable Long postId) {
        Long memberId = authUser.getId();
        return postLikeService.togglePostLike(memberId,postId);
    }
}
