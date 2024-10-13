package com.terror.springcommunity.service.postLike;

import com.terror.springcommunity.constans.response.ApiResponsePostLikeEnum;
import com.terror.springcommunity.entity.Member;
import com.terror.springcommunity.entity.Post;
import com.terror.springcommunity.entity.PostLike;
import com.terror.springcommunity.model.apiResponse.ApiResponse;
import com.terror.springcommunity.repository.PostLikeRepository;
import com.terror.springcommunity.service.post.PostServiceImpl;
import com.terror.springcommunity.service.member.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostLikeServiceImpl implements PostLikeService {
    private final MemberServiceImpl memberService;
    private final PostServiceImpl postService;
    private final PostLikeRepository postLikeRepository;

    @Transactional
    public ApiResponse togglePostLike(Long memberId, Long postId) {
        Member member = memberService.findByMemberId(memberId);
        Post post = postService.findByPostId(postId);
        boolean exists = existsByMemberAndPost(member, post);
        // 존재한다면 없애기
        if (exists) {
            PostLike postLike = findByMemberAndPost(member, post);
            postLikeRepository.delete(postLike);
            return new ApiResponse(ApiResponsePostLikeEnum.POST_LIKE_DELETE_SUCCESS);
        // 존재하지 않는다면 like 저장시키기
        } else {
            PostLike postLike = new PostLike(member,post);
            postLikeRepository.save(postLike);
            return new ApiResponse(ApiResponsePostLikeEnum.POST_LIKE_SUCCESS);
        }
    }

    // like 존재 여부 확인
    public boolean existsByMemberAndPost(Member member, Post post) {
        return postLikeRepository.existsByMemberAndPost(member,post);
    }

    // like 엔티티 가져오기
    public PostLike findByMemberAndPost(Member member, Post post) {
        return postLikeRepository.findByMemberAndPost(member,post);
    }
}
