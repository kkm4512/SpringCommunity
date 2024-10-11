package com.terror.springcommunity.mapper;

import com.terror.springcommunity.entity.Comment;
import com.terror.springcommunity.entity.Post;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostMapper {
    @Select("SELECT p.*, c.* " +
            "FROM post p " +
            "LEFT JOIN member m ON p.member_id = m.id " +
            "LEFT JOIN comment c ON p.id = c.post_id " +
            "WHERE p.member_id = #{memberId}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "created_at", property = "createdAt"),
            @Result(column = "updated_at", property = "updatedAt"),
            @Result(property = "commentList", column = "id",
                    many = @Many(select = "findCommentsByPostId"))
    })
    List<Post> findPostsByMemberId(Long memberId);

    @Select("SELECT * FROM comment WHERE post_id = #{postId}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "content", property = "content"),
            @Result(column = "created_at", property = "createdAt"),
            @Result(column = "updated_at", property = "updatedAt"),
    })
    List<Comment> findCommentsByPostId(Long postId);
}

