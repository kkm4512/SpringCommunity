package com.terror.springcommunity.model.apiResponse.post;

import com.terror.springcommunity.constans.ApiResponseMemberEnum;
import com.terror.springcommunity.model.member.MemberResponseDto;
import com.terror.springcommunity.model.post.PostResponseDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ApiResponsePosts {
    private final int status;
    private final String message;
    private final List<PostResponseDto> posts = new ArrayList<>();

    public ApiResponsePosts(ApiResponseMemberEnum apiResponseEnum, List<PostResponseDto> posts) {
        this.status = apiResponseEnum.getStatus().value();
        this.message = apiResponseEnum.getMessage();
        this.posts.addAll(posts);
    }
}
