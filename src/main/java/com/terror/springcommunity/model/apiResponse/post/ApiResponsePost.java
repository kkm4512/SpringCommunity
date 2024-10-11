package com.terror.springcommunity.model.apiResponse.post;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.terror.springcommunity.constans.ApiResponsePostEnum;
import com.terror.springcommunity.model.post.PostResponseDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ApiResponsePost {
    private int status;
    private String message;
    private final List<PostResponseDto> postData = new ArrayList<>();

    public ApiResponsePost(ApiResponsePostEnum apiResponsePost, List<PostResponseDto> postData) {
        this.status = apiResponsePost.getStatus().value();
        this.message = apiResponsePost.getMessage();
        this.postData.addAll(postData);
    }

}
