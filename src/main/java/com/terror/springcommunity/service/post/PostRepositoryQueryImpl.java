package com.terror.springcommunity.service.post;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.terror.springcommunity.entity.Member;
import com.terror.springcommunity.entity.Post;
import com.terror.springcommunity.entity.QPost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PostRepositoryQueryImpl implements PostRepositoryQuery {
    private final JPAQueryFactory jpaQueryFactory;
    QPost post = QPost.post;

    @Override
    public List<Post> findAllPostsByMember(Member member) {
        return jpaQueryFactory.select(post)
                .from(post)
                .leftJoin(post.member).fetchJoin()
                .leftJoin(post.commentList).fetchJoin()
                .where(post.member.eq(member))
                .fetch();
    }
}
