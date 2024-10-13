package com.terror.springcommunity.service.post;

import com.terror.springcommunity.constans.response.ApiResponsePostEnum;
import com.terror.springcommunity.entity.Member;
import com.terror.springcommunity.entity.Post;
import com.terror.springcommunity.exception.PostException;
import com.terror.springcommunity.model.apiResponse.post.ApiResponsePost;
import com.terror.springcommunity.model.post.PostRequestDto;
import com.terror.springcommunity.model.post.PostResponseDto;
import com.terror.springcommunity.repository.PostRepository;
import com.terror.springcommunity.service.member.MemberService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final MemberService memberService;
    private final static List<PostResponseDto> emptyPosts = new ArrayList<>();

    public PostServiceImpl(
            PostRepository postRepository,
            MemberService memberService
    ) {
        this.postRepository = postRepository;
        this.memberService = memberService;
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(value = "getMyPosts",key = "'memberId:' + #memberId", cacheManager = "rcm"),
            @CacheEvict(value = "getMyPostsWithOtherPosts",allEntries = true, cacheManager = "rcm")
    })
    public ApiResponsePost createPost(Long memberId, PostRequestDto postRequestDto) {
        Member member = memberService.findByMemberId(memberId);
        Post post = Post.fromPostRequestDto(postRequestDto);
        post.addMember(member);
        postRepository.save(post);
        return new ApiResponsePost(ApiResponsePostEnum.POST_SAVE_SUCCESS, emptyPosts);
    }

    @Cacheable(value = "getMyPosts", key = "'memberId:' + #memberId",sync = true, cacheManager = "rcm")
    @Transactional(readOnly = true)
    public ApiResponsePost getMyPosts(Long memberId, Pageable pageable) {
        Member member = memberService.findByMemberId(memberId);
        Page<Post> pagePosts = postRepository.findAllByMember(member, pageable);
        List<PostResponseDto> posts = pagePosts.map(PostResponseDto::new).stream().toList();
        return new ApiResponsePost(ApiResponsePostEnum.POST_GET_SUCCESS, posts);
    }

    @Cacheable(value = "getMyPostsWithOtherPosts", key = "'post_all_page:' + #pageable.pageNumber" , sync = true, cacheManager = "rcm")
    @Transactional(readOnly = true)
    public ApiResponsePost getMyPostsWithOtherPosts(Pageable pageable) {
        Page<Post> posts = postRepository.findAllBy(pageable);
        List<PostResponseDto> postList = posts.map(PostResponseDto::new).stream().toList();
        return new ApiResponsePost(ApiResponsePostEnum.POST_GET_SUCCESS, postList);
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(value = "getMyPosts",key = "'memberId:' + #memberId", cacheManager = "rcm"),
            @CacheEvict(value = "getMyPostsWithOtherPosts",allEntries = true, cacheManager = "rcm")
    })
    public ApiResponsePost deletePost(Long memberId, Long postId) {
        Member member = memberService.findByMemberId(memberId);
        Post post = findByPostId(postId);
        post.isWrittenByMember(member);
        postRepository.delete(post);
        return new ApiResponsePost(ApiResponsePostEnum.POST_DELETE_SUCCESS, emptyPosts);
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(value = "getMyPosts",key = "'memberId:' + #memberId", cacheManager = "rcm"),
            @CacheEvict(value = "getMyPostsWithOtherPosts",allEntries = true, cacheManager = "rcm")
    })
    public ApiResponsePost updatePost(Long memberId, Long postId, PostRequestDto reqDto) {
        Member member = memberService.findByMemberId(memberId);
        Post post = findByPostId(postId);
        List<Post> pagePosts = postRepository.findAll();
        List<PostResponseDto> posts = pagePosts.stream().map(PostResponseDto::new).toList();
        post.isWrittenByMember(member);
        post.updatePost(reqDto);
        return new ApiResponsePost(ApiResponsePostEnum.POST_UPDATE_SUCCESS, posts);
    }

    /**
     * 게시글의 id로 게시글 찾고 있다면 게시글 반환
     *
     * @param postId 조회하고자 하는 게시글의 ID
     * @return 조회 게시글 반환
     * @throws PostException 찾고자 하는 게시굴이 없을 경우 발생되는 예외
     */

    @Transactional(readOnly = true)
    public Post findByPostId(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new PostException(ApiResponsePostEnum.POST_GET_FAIL));
    }

}
