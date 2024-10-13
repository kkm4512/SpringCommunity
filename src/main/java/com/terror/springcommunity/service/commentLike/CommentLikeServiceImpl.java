package com.terror.springcommunity.service.commentLike;

import com.terror.springcommunity.constans.response.ApiResponseCommentLikeEnum;
import com.terror.springcommunity.entity.Comment;
import com.terror.springcommunity.entity.CommentLike;
import com.terror.springcommunity.entity.Member;
import com.terror.springcommunity.entity.Post;
import com.terror.springcommunity.exception.CommentException;
import com.terror.springcommunity.exception.MemberException;
import com.terror.springcommunity.model.apiResponse.ApiResponse;
import com.terror.springcommunity.repository.CommentLikeRepository;
import com.terror.springcommunity.repository.MemberRepository;
import com.terror.springcommunity.service.comment.CommentService;
import com.terror.springcommunity.service.member.MemberService;
import com.terror.springcommunity.service.post.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j(topic = "CommentLikeServiceImpl")
@RequiredArgsConstructor
public class CommentLikeServiceImpl implements CommentLikeService{
    private final CommentLikeRepository commentLikeRepository;
    private final PostService postService;
    private final MemberService memberService;
    private final CommentService commentService;
    private final MemberRepository memberRepository;

    /**
     * 댓글 좋아요 기능
     *
     * @param memberId 어떤 유저가 좋아요 눌렀는지 알기 위함
     * @param commentId 어떤 댓글에 좋아욘 눌렀는지 알기 위함
     * @return ApiResponseComment 성공응답 형식
     * @throws MemberException 유저 못찾을때 발생되는 예외
     * @throws CommentException 댓글 못찾을때 발생되는 예외
     */
    @Override
    @Transactional
    public ApiResponse toggleLike(Long memberId, Long commentId, Long postId) {
        Member member = memberService.findByMemberId(memberId);
        Post post = postService.findByPostId(postId);
        Comment comment = commentService.findByCommentId(commentId);
        boolean exists = isCommentAndMember(comment, member);
        CommentLike commentLike = CommentLike.builder()
                .comment(comment)
                .member(member)
                .post(post)
                .build();
        // 좋아요가 눌러져 있다면 지우기
        if (exists) {
            CommentLike findComment =  findCommentAndMember(comment,member);
            commentLikeRepository.delete(findComment);
            return new ApiResponse(ApiResponseCommentLikeEnum.COMMENT_LIKE_DELETE_SUCCESS);
        }
        // 좋아요가 안눌러져 있다면 만들기
        else {
            commentLikeRepository.save(commentLike);
            return new ApiResponse(ApiResponseCommentLikeEnum.COMMENT_LIKE_SAVE_SUCCESS);
        }
    }

    // 클라이언트로 부터 요청된 유저가 어떤 댓글에 좋아요를 눌렀을때를 식별하기 위함
    private boolean isCommentAndMember(Comment comment, Member member) {
        return commentLikeRepository.existsByCommentAndMember(comment, member);
    }

    private CommentLike findCommentAndMember(Comment comment, Member member) {
        return commentLikeRepository.findByCommentAndMember(comment, member);
    }
}
