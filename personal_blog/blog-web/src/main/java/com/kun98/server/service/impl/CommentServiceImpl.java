package com.kun98.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kun98.common.po.Comment;
import com.kun98.server.mapper.CommentMapper;
import com.kun98.server.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LIU JIANKUN
 * @create 2022-03-23 17:29
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    @Cacheable(value = {"CommentMap"}, key = "#blogId")
    public List<Comment> getCommentList(Long blogId) {
        // 获取父级评论的list
        List<Comment> comments1 = commentMapper.selectRootList(blogId);
        // 获取子级评论的list
        List<Comment> comments2 = commentMapper.selectChildList(blogId);
        return combineChildren(comments1, comments2);
    }

    @CacheEvict(value = {"CommentMap"}, key = "#comment.blogId")
    public void replyComment(Comment comment, Long uid) {
        comment.setUid(uid);
        if (comment.getParentCommentId() == null) {
            comment.setParentCommentId(-1L);
        }
        commentMapper.insert(comment);
    }

    @CacheEvict(value = {"CommentMap"}, key = "#blogId")
    public void delComment(Long blogId, Long commentId, Long uid) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", uid)
                .eq("blog_id", blogId)
                .eq("comment_id", commentId);
        if (commentMapper.selectOne(wrapper) == null) {
            throw new RuntimeException("您删除的评论不是你发布的，你无权删除！");
        }
        // 获取被删的根评论
        // 递归删除该评论以及其子评论
        List<Long> idList = new ArrayList<>();
        getDelIdList(commentId, idList);
        idList.add(commentId);
        commentMapper.deleteBatchIds(idList);
    }

    /**
     * 获取递归删除的id的list
     */
    public void getDelIdList(Long commentId, List<Long> idList) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_comment_id",commentId)
                .select("comment_id");
        List<Comment> childIdList = commentMapper.selectList(wrapper);
        //把childIdList里面菜单id值获取出来，封装idList里面，做递归查询
        childIdList.stream().forEach(item -> {
            //封装idList里面
            idList.add(item.getCommentId());
            //递归查询
            this.getDelIdList(item.getCommentId(),idList);
        });
    }

    /**
     * 把子评论加入到父评论的children
     *
     * @param rootList
     * @param childList
     * @return list
     */
    public List<Comment> combineChildren(List<Comment> rootList, List<Comment> childList) {
        //对于每一个父级评论，在childList里选出来所有parentCommentId==root.commentId的评论
        for (Comment root : rootList) {

            List<Comment> comments = new ArrayList<>();
            for (Comment child : childList) {
                if (child.getParentCommentId().equals(root.getCommentId())) {
                    comments.add(child);
                }
            }
            //选出来所有root的子级评论，再继续递归查找更深一级的子级评论
            List<Comment> comments1 = combineChildren(comments, childList);
            root.setChildren(comments1);
        }
        return rootList;
    }
}
