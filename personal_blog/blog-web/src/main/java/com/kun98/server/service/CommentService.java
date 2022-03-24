package com.kun98.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kun98.common.po.Comment;

import java.util.List;

/**
 * @author LIU JIANKUN
 * @create 2022-03-23 17:24
 */
public interface CommentService extends IService<Comment> {

    List<Comment> getCommentList(Long blogId);

    void replyComment(Comment comment, Long uid);

    void delComment(Long blogId, Long commentId, Long uid);
}
