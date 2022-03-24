package com.kun98.server.controller;

import com.kun98.common.entity.Result;
import com.kun98.common.entity.ResultInfo;
import com.kun98.common.po.Comment;
import com.kun98.common.po.User;
import com.kun98.server.annotations.LoginRequired;
import com.kun98.server.service.CommentService;
import com.netflix.discovery.converters.Auto;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author LIU JIANKUN
 * @create 2022-03-23 17:18
 */
@RestController
@RequestMapping("/comment")
@CrossOrigin
@Api("博客-评论区功能")
@Slf4j
public class CommentController {


    @Autowired
    CommentService commentService;

    @GetMapping("/{blogId}")//获取该博客下的所有评论
    public Result getCommentList(@PathVariable("blogId") Long blogId) {
        List<Comment> list = commentService.getCommentList(blogId);
        return Result.success().codeAndMessage(ResultInfo.SUCCESS).data(list);
    }


    @LoginRequired
    @PostMapping("/replyComment")
    public Result replyComment(@RequestBody Comment comment, HttpServletRequest request) {
        User user = (User) request.getAttribute("currentUser");
        commentService.replyComment(comment, user.getUid());
        return Result.success().codeAndMessage(ResultInfo.SUCCESS);
    }

    @LoginRequired
    @DeleteMapping("/{blogId}/{commentId}")
    public Result delComment(@PathVariable Long blogId, @PathVariable Long commentId, HttpServletRequest request) {
        User user = (User) request.getAttribute("currentUser");
        commentService.delComment(blogId, commentId, user.getUid());
        return Result.success().codeAndMessage(ResultInfo.SUCCESS);
    }
}
