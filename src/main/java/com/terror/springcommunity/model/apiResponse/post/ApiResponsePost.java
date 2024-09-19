package com.terror.springcommunity.model.apiResponse.post;

import com.terror.springcommunity.constans.ApiResponsePostEnum;
import com.terror.springcommunity.model.post.PostResponseDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ApiResponsePost {
    private final int status;
    private final String message;
    private final List<PostResponseDto> postData = new ArrayList<>();

    public ApiResponsePost(ApiResponsePostEnum apiResponsePost, List<PostResponseDto> postData) {
        this.status = apiResponsePost.getStatus().value();
        this.message = apiResponsePost.getMessage();
        this.postData.addAll(postData);
    }
}
