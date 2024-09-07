package com.terror.springcommunity.model.apiResponse.member;

import com.terror.springcommunity.constans.ApiResponseEnum;
import com.terror.springcommunity.model.member.MemberResponseDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ApiResponseMemberInfos {
    private final int status;
    private final String message;
    private final List<MemberResponseDto> memberInfos = new ArrayList<>();

    public ApiResponseMemberInfos(ApiResponseEnum apiResponseEnum, List<MemberResponseDto> memberInfos) {
        this.status = apiResponseEnum.getStatus().value();
        this.message = apiResponseEnum.getMessage();
        this.memberInfos.addAll(memberInfos);
    }
}
