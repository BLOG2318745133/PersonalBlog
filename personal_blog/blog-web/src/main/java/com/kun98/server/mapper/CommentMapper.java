package com.kun98.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kun98.common.po.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LIU JIANKUN
 * @create 2022-03-23 17:29
 */
@Repository
public interface CommentMapper extends BaseMapper<Comment> {
    /**
     * 将根节点评论封装到list
     * @return
     */
    @Select("SELECT u.nickname, u.avatar, c.comment_id, c.uid, c.content, c.create_time, c.blog_id, c.parent_comment_id " +
            "FROM comment c, user u " +
            "WHERE c.uid = u.uid AND c.blog_id = #{blogId} AND c.parent_comment_id = -1 ")
    List<Comment> selectRootList(Long blogId);

    /**
     * 将不是根节点评论封装到list
     * @return
     */
    @Select("SELECT u.nickname, u.avatar, c.comment_id, c.uid, c.content, c.create_time, c.blog_id, c.parent_comment_id " +
            "FROM comment c, user u " +
            "WHERE c.uid = u.uid AND c.blog_id = #{blogId} AND c.parent_comment_id != -1 ")
    List<Comment> selectChildList(Long blogId);

    @Delete("DELETE c " +
            "FROM comment c " +
            "WHERE c.blog_id = #{blogId}")
    void deleteByBlogId(Long blogId);
}
