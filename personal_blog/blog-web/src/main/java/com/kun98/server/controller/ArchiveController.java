package com.kun98.server.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kun98.common.entity.QueryPageBean;
import com.kun98.common.entity.Result;
import com.kun98.common.entity.ResultInfo;
import com.kun98.common.po.User;
import com.kun98.common.vo.BlogVo;
import com.kun98.server.annotations.LoginRequired;
import com.kun98.server.service.BlogService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LIU JIANKUN
 * @create 2022-03-21 23:26
 */
@RestController
@RequestMapping("/archives")
@CrossOrigin
@Api("博客-归档")
@Slf4j
public class ArchiveController {

    @Autowired
    BlogService blogService;

    @LoginRequired
    @GetMapping("/getArchivesList")
    public Result getArchivesList(HttpServletRequest request){
        User user = (User) request.getAttribute("currentUser");
        QueryPageBean queryPageBean = new QueryPageBean();
        queryPageBean.setCurrentPage(1);
        queryPageBean.setPageSize(20);
        Page<BlogVo> page = blogService.getArchiveList(queryPageBean,user.getUid());
        return Result.success().codeAndMessage(ResultInfo.SUCCESS).data(page);
    }
}
