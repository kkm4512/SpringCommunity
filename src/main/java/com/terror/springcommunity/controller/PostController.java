package com.terror.springcommunity.controller;

import com.terror.springcommunity.model.apiResponse.post.ApiResponsePost;
import com.terror.springcommunity.model.post.PostRequestDto;
import com.terror.springcommunity.security.AuthUser;
import com.terror.springcommunity.service.post.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j(topic = "/api/posts")
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ApiResponsePost createPost(@AuthenticationPrincipal AuthUser authUser, @RequestBody PostRequestDto reqDto){
        Long memberId = authUser.getId();
        return postService.createPost(memberId,reqDto);
    }

    // 자신의 게시글들 조회 (1페이지당, 10개)
    @Secured("USER")
    @GetMapping("/mine")
    public ApiResponsePost getMyPosts(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestParam (required = false, defaultValue = "0") int page,
            @RequestParam (required = false, defaultValue = "10") int size,
            @RequestParam (required = false, defaultValue = "desc") String sort
    ){
        Long memberId = authUser.getId();
        Sort.Direction direction = Sort.Direction.fromString(sort);
        Pageable pageable = PageRequest.of(page, size, direction,"createdAt");
        return postService.getMyPosts(memberId,pageable);
    }

    // 자신을 포함한, 다른사람들 게시글들 조회 (1페이지당, 10개)
    @GetMapping("/feed")
    public ApiResponsePost getMyPostsWithOtherPosts(
            @RequestParam (required = false, defaultValue = "0") int page,
            @RequestParam (required = false, defaultValue = "10") int size,
            @RequestParam (required = false, defaultValue = "desc") String sort
    ){
        Sort.Direction direction = Sort.Direction.fromString(sort);
        Pageable pageable = PageRequest.of(page, size, direction,"createdAt");
        return postService.getMyPostsWithOtherPosts(pageable);
    }

    // 게시글 삭제 (본인 이여야함)
    @DeleteMapping("/{postId}")
    public ApiResponsePost deletePost(
        @AuthenticationPrincipal AuthUser authUser,
        @PathVariable Long postId
    ){
        Long memberId = authUser.getId();
        return postService.deletePost(memberId,postId);
    }

    // 게시글 수정 (본인 이여야함)
    @PutMapping("/{postId}")
    public ApiResponsePost updatePost(
            @AuthenticationPrincipal AuthUser authUser,
            @PathVariable Long postId,
            @RequestBody PostRequestDto reqDto
    ){
        Long memberId = authUser.getId();
        return postService.updatePost(memberId,postId,reqDto);
    }
}
