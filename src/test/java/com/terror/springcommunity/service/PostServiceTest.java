package com.terror.springcommunity.service;

import com.terror.springcommunity.common.TestUserEnum;
import com.terror.springcommunity.constans.response.ApiResponseMemberEnum;
import com.terror.springcommunity.entity.Member;
import com.terror.springcommunity.exception.MemberException;
import com.terror.springcommunity.mapper.PostMapper;
import com.terror.springcommunity.model.apiResponse.post.ApiResponsePost;
import com.terror.springcommunity.model.member.SignUpDto;
import com.terror.springcommunity.model.post.PostRequestDto;
import com.terror.springcommunity.repository.MemberRepository;
import com.terror.springcommunity.repository.PostRepository;
import com.terror.springcommunity.service.member.MemberService;
import com.terror.springcommunity.service.post.PostServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class) // @Mock 사용을 위해 설정합니다.
class PostServiceTest {
    Member testMember1;
    Member testMember2;
    Long testMember1Id;
    Long testMember2Id;
    PostRequestDto testPostRequestDto1;
    PostRequestDto testPostRequestDto2;

    @Mock
    MemberRepository memberRepository;

    @Mock
    PostRepository postRepository;

    @Mock
    MemberService memberService;

    @Mock
    PostMapper postMapper;

    @InjectMocks
    PostServiceImpl postService;

    @BeforeEach
    void setUp() {
        // 테스트 유저 생성 1
        TestUserEnum tm1 = TestUserEnum.TEST_MEMBER_ONE;
        SignUpDto signUpDto1 = new SignUpDto(tm1.getUsername(),tm1.getPassword(),tm1.getEmail(),tm1.getAuthor(),tm1.getRole());
        testMember1 = Member.fromSignUpDto(signUpDto1);
        ReflectionTestUtils.setField(testMember1, "id", 1L);
        testMember1Id = testMember1.getId();

        // 테스트 유저 생성 2
        TestUserEnum tm2 = TestUserEnum.TEST_MEMBER_TWO;
        SignUpDto signUpDto2 = new SignUpDto(tm2.getUsername(),tm2.getPassword(),tm2.getEmail(),tm2.getAuthor(),tm2.getRole());
        testMember2 = Member.fromSignUpDto(signUpDto2);
        ReflectionTestUtils.setField(testMember2, "id", 2L);
        testMember2Id = testMember2.getId();

        // 테스트 게시글 생성 1,2
        testPostRequestDto1 = PostRequestDto.of("테스트 제목1", "테스트 본문1");
        testPostRequestDto2 = PostRequestDto.of("테스트 제목2", "테스트 본문2");
    }

    @Nested
    public class 게시글_테스트 {

        @Test
        @DisplayName("게시글 생성 성공")
        void test1() {
            //given - 멤버 조회시, 특정 멤버가 나오게 상황 부여
            given(memberService.findByMemberId(testMember1Id)).willReturn(testMember1);
            String expectedMessage = "게시글 저장에 성공 하였습니다";

            //when - 실제 게시글 생성 작업 시도
            ApiResponsePost result = postService.createPost(testMember1Id, testPostRequestDto1);

            //then - 각 메서드가 한번씩 호출됐는지 검증
            assertEquals(
                    expectedMessage,
                    result.getMessage()
            );
        }

        @Test
        @DisplayName("게시글 생성 실패, 멤버 찾기 실패")
        void test2() {
            //given - 멤버 조회시, 멤버 미조회 상황 부여
            given(memberService.findByMemberId(testMember1Id)).willThrow(MemberException.class);

            //given - 멤버 미조회 실패 예외 준비
            MemberException expectedException = new MemberException(ApiResponseMemberEnum.MEMBER_NOT_FOUND);

            //when - 실제 게시글 생성 작업 시도
            MemberException actualException = assertThrows(MemberException.class, () ->
                postService.createPost(testMember1Id, testPostRequestDto2)
            );

            //then - 서로가 같은 exception 인지 확인, 각 메서드 호출 횟수가 일치하는지 확인
            assertEquals(
                    expectedException.getClass(),
                    actualException.getClass());
        }
    }
}