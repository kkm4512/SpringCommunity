package com.terror.springcommunity.controller;

import com.terror.springcommunity.entity.Member;
import com.terror.springcommunity.model.apiResponse.ApiResponse;
import com.terror.springcommunity.model.post.PostRequestDto;
import com.terror.springcommunity.security.UserDetailsImpl;
import com.terror.springcommunity.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    private ResponseEntity<ApiResponse> createPost(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody PostRequestDto reqDto){
        Member member = userDetails.getMember();
        ApiResponse apiResponse = postService.createPost(member,reqDto);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }
}
