package com.kun98.server.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kun98.common.entity.QueryPageBean;
import com.kun98.common.entity.Result;
import com.kun98.common.entity.ResultInfo;
import com.kun98.common.po.Blog;
import com.kun98.common.vo.BlogVo;
import com.kun98.common.vo.TagVO;
import com.kun98.common.vo.TypeVO;
import com.kun98.server.service.BlogService;
import com.kun98.server.service.TagService;
import com.kun98.server.service.TypeService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LIU JIANKUN
 * @create 2022-03-20 22:49
 */
@RestController
@RequestMapping("/home")
@CrossOrigin
@Api("博客首页-展示")
@Slf4j
public class HomeController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TagService tagService;
    @Autowired
    private TypeService typeService;


    @PostMapping("/findHomePage")
    public Result findHomePage(@RequestBody QueryPageBean queryPageBean){
        Page<BlogVo> homePage = blogService.findHomePage(queryPageBean);
        return Result.success().codeAndMessage(ResultInfo.SUCCESS).data(homePage);

    }

    @GetMapping("/getTypeCount")
    public Result getTypeCount() {
        List<TypeVO> list = typeService.getTypeCount();
        return Result.success().codeAndMessage(ResultInfo.SUCCESS).data(list);
    }

    @GetMapping("/getTagCount")
    public Result getTagCount() {
        List<TagVO> list = tagService.getTagCount();
        return Result.success().codeAndMessage(ResultInfo.SUCCESS).data(list);
    }

    @GetMapping("/latestList")
    public Result getRecommendList() {
        List<Blog> blogList = blogService.getLatestList();
        return Result.success().codeAndMessage(ResultInfo.SUCCESS).data(blogList);
    }


}
