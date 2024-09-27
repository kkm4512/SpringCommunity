package com.terror.springcommunity.controller;

import com.terror.springcommunity.model.apiResponse.post.ApiResponsePost;
import com.terror.springcommunity.model.post.PostRequestDto;
import com.terror.springcommunity.security.UserDetailsImpl;
import com.terror.springcommunity.service.post.PostService;
import com.terror.springcommunity.service.post.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j(topic = "/api/posts")
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ApiResponsePost createPost(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody PostRequestDto reqDto){
        Long memberId = userDetails.getId();
        return postService.createPost(memberId,reqDto);
    }

    // 자신의 게시글들 조회 (1페이지당, 10개)
    @GetMapping("/mine")
    public ApiResponsePost getMyPosts(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam (required = false, defaultValue = "0") int page,
            @RequestParam (required = false, defaultValue = "10") int size,
            @RequestParam (required = false, defaultValue = "desc") String sort
    ){
        Long memberId = userDetails.getId();
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
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @PathVariable Long postId
    ){
        Long memberId = userDetails.getId();
        return postService.deletePost(memberId,postId);
    }

    // 게시글 수정 (본인 이여야함)
    @PutMapping("/{postId}")
    public ApiResponsePost updatePost(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long postId,
            @RequestBody PostRequestDto reqDto
    ){
        Long memberId = userDetails.getId();
        return postService.updatePost(memberId,postId,reqDto);
    }

    // 양방향 매핑을 이용했을때의 조회 속도
    @GetMapping("/jpaInJava")
    public ApiResponsePost getMyPostsJava(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        Long memberId = userDetails.getId();
        return postService.getAllPostJpaInJava(memberId);
    }

    // 양방향 매핑을 이용하지 않을때의 속도
    @GetMapping("/jpaOutJava")
    public ApiResponsePost getMyPostsNoJava(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        Long memberId = userDetails.getId();
        return postService.getAllPostJpaInJava(memberId);
    }

    // MyBatis를 활용한 조회속도
    @GetMapping("/myBatis")
    public ApiResponsePost getMyPostsMyBatis(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        Long memberId = userDetails.getId();
        return postService.getAllPostMyBatis(memberId);
    }

    // queryDsl로 조회한 속도
    @GetMapping("/queryDsl")
    public ApiResponsePost getMyPostsQueryDsl(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        Long memberId = userDetails.getId();
        return postService.getAllPostQueryDsl(memberId);
    }
}
