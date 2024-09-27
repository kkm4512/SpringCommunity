package com.terror.springcommunity.service.comment;

import com.terror.springcommunity.constans.ApiResponseCommentEnum;
import com.terror.springcommunity.entity.Comment;
import com.terror.springcommunity.entity.Member;
import com.terror.springcommunity.entity.Post;
import com.terror.springcommunity.exception.CommentException;
import com.terror.springcommunity.model.apiResponse.comment.ApiResponseComment;
import com.terror.springcommunity.model.comment.CommentRequestDto;
import com.terror.springcommunity.model.comment.CommentResponseDto;
import com.terror.springcommunity.repository.CommentRepository;
import com.terror.springcommunity.service.member.MemberServiceImpl;
import com.terror.springcommunity.service.post.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostServiceImpl postService;
    private final static List<CommentResponseDto> emptyComments = new ArrayList<>();
    private final MemberServiceImpl memberService;


    @Transactional
    public ApiResponseComment createComment(Long postId, Long memberId, CommentRequestDto reqDto) {
        Member member = memberService.findByMemberId(memberId);
        Post post = postService.findByPostId(postId);
        Comment comment = Comment.from(reqDto);
        comment.addMember(member);
        post.addComment(comment);
        commentRepository.save(comment);
        return new ApiResponseComment(ApiResponseCommentEnum.COMMENT_SAVE_SUCCESS,emptyComments);
    }

    @Transactional
    public ApiResponseComment deleteComment(Long commentId, Long memberId) {
        Member member = memberService.findByMemberId(memberId);
        Comment comment = findByCommentId(commentId);
        comment.isWrittenMember(member);
        commentRepository.delete(comment);
        return new ApiResponseComment(ApiResponseCommentEnum.COMMENT_DELETE_SUCCESS,emptyComments);
    }

    @Transactional
    public ApiResponseComment updateComment(Long commentId, Long memberId, CommentRequestDto reqDto) {
        Member member = memberService.findByMemberId(memberId);
        Comment comment = findByCommentId(commentId);
        comment.isWrittenMember(member);
        comment.updateComment(reqDto);
        return new ApiResponseComment(ApiResponseCommentEnum.COMMENT_UPDATE_SUCCESS,emptyComments);
    }

    public Comment findByCommentId(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new CommentException(ApiResponseCommentEnum.COMMENT_GET_FAIL));
    }
}
