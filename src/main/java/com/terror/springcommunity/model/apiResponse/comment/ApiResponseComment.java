package com.terror.springcommunity.model.apiResponse.comment;

import com.terror.springcommunity.constans.ApiResponseCommentEnum;
import com.terror.springcommunity.constans.ApiResponseMemberEnum;
import com.terror.springcommunity.model.comment.CommentResponseDto;
import com.terror.springcommunity.model.member.MemberResponseDto;
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
