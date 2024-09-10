package com.terror.springcommunity.service;

import com.terror.springcommunity.constans.UserRoleEnum;
import com.terror.springcommunity.entity.Member;
import com.terror.springcommunity.model.apiResponse.ApiResponse;
import com.terror.springcommunity.model.member.SignUpDto;
import com.terror.springcommunity.model.post.PostRequestDto;
import com.terror.springcommunity.repository.MemberRepository;
import com.terror.springcommunity.repository.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // @Mock 사용을 위해 설정합니다.
class PostServiceTest {
    @Mock
    PostService postService;

    @Mock
    MemberRepository memberRepository;

    @Mock
    PostRepository postRepository;

    @Test
    @DisplayName("게시글 생성 및 연관관계 설정 테스트")
    void test1(){
        //given
        SignUpDto signUpDto = new SignUpDto(
                "test",
                "!@skdud340",
                "test@naver.com",
                "김경민",
                UserRoleEnum.USER
        );
        Member member = new Member(signUpDto);
        PostRequestDto postRequestDto = new PostRequestDto("제목","내용");

        //when
        postService.createPost(member,postRequestDto);

        //then
        verify(memberRepository, times(1)).save(member);

    }
}