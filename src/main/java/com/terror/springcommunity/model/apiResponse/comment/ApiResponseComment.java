package com.terror.springcommunity.model.apiResponse.comment;

import com.terror.springcommunity.constans.response.ApiResponseCommentEnum;
import com.terror.springcommunity.model.comment.CommentResponseDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ApiResponseComment {
    private final int status;
    private final String message;
    private final List<CommentResponseDto> comments = new ArrayList<>();

    public ApiResponseComment(ApiResponseCommentEnum apiResponseEnum, List<CommentResponseDto> comments) {
        this.status = apiResponseEnum.getStatus().value();
        this.message = apiResponseEnum.getMessage();
        this.comments.addAll(comments);
    }
}
