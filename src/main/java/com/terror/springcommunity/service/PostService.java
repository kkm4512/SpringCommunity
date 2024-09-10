package com.terror.springcommunity.service;

import com.terror.springcommunity.constans.ApiResponsePostEnum;
import com.terror.springcommunity.entity.Member;
import com.terror.springcommunity.entity.Post;
import com.terror.springcommunity.model.apiResponse.ApiResponse;
import com.terror.springcommunity.model.post.PostRequestDto;
import com.terror.springcommunity.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final MemberRepository memberRepository;

    @Transactional
    public ApiResponse createPost(Member member, PostRequestDto reqDto) {
        Post post = new Post(reqDto);
        member.addPost(post);
        memberRepository.save(member);
        return new ApiResponse(ApiResponsePostEnum.POST_SAVE_SUCCESS);
    }
}
