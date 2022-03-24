package com.kun98.server.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kun98.common.entity.QueryPageBean;
import com.kun98.common.entity.Result;
import com.kun98.common.entity.ResultInfo;
import com.kun98.common.po.Blog;
import com.kun98.common.po.User;
import com.kun98.common.vo.AddBlogVo;
import com.kun98.common.vo.BlogVo;
import com.kun98.server.annotations.IPRequired;
import com.kun98.server.annotations.LoginRequired;
import com.kun98.server.service.BlogService;
import com.kun98.server.service.BlogTagService;
import com.kun98.server.service.ViewsService;
import com.kun98.server.utils.BeanUtilsIgnoreNull;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author LIU JIANKUN
 * @create 2022-03-17 22:46
 */
@Api(value = "后台管理-博客管理")
@RestController
@CrossOrigin
@RequestMapping("/blog")
@Slf4j
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private ViewsService viewsService;

    @Autowired
    BlogTagService blogTagService;

    @LoginRequired
    @ApiOperation(value = "分页查询", notes = "返回分页数据")
    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean, HttpServletRequest request) {
        //取出在interceptor中放在request域中的currentUser
        User user = (User) request.getAttribute("currentUser");

//        log.info(user.toString());
        Page<BlogVo> blogVoPage = blogService.findPage(queryPageBean, user.getUid());

        return Result.success().codeAndMessage(ResultInfo.SUCCESS).data(blogVoPage);
    }

    /**
     * 添加博客
     * @param addBlogVo
     * @param request
     * @return
     */
    @LoginRequired
    @PostMapping("/add")
    public Result addBlog(@RequestBody AddBlogVo addBlogVo, HttpServletRequest request) {
        User user = (User) request.getAttribute("currentUser");

        boolean flag = blogService.addBlog(addBlogVo, user.getUid());
        if(flag){
            return Result.success().codeAndMessage(ResultInfo.SUCCESS);
        }else{
            return Result.fail().code("400").message("Failed to add new blog ");
        }
    }



    @IPRequired
    @GetMapping("/{blogId}")
    public Result getOneBlog(@PathVariable("blogId") Long blogId, HttpServletRequest request) {
//        viewsService.addViews(blogId, (String) request.getAttribute("host"));
        blogService.setViews(blogId);
        BlogVo blogVo = blogService.getOneBlog(blogId);
        return Result.success().codeAndMessage(ResultInfo.SUCCESS).data(blogVo);
    }

    @GetMapping("/{blogId}/{uid}")
    @LoginRequired
    public Result thumbsUp(@PathVariable("blogId") Long blogId, @PathVariable("uid") Long uid) {
        try {
            blogService.thumbsUp(blogId, uid);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail().code("400").message("cancel thumbs up");
        }
        return Result.success().codeAndMessage("200","thumbs up success");
    }

    @DeleteMapping("/deleteBlog/{blogId}")
    @LoginRequired
    public Result deleteBlog(@PathVariable("blogId")Long blogId, HttpServletRequest request){
        User user = (User) request.getAttribute("currentUser");
        boolean deleteBlog = blogService.deleteBlog(blogId, user.getUid());
        if(deleteBlog){
            return Result.success().codeAndMessage(ResultInfo.SUCCESS);
        }else{
            return Result.fail().code("400").message("Fail to delete the blog");
        }
    }


    @GetMapping("/getBlogInfoForUpdate/{blogIdToUpdate}")
    @LoginRequired
    public Result getBlogInfoForUpdate(@PathVariable("blogIdToUpdate")Long blogId){
        Blog blog = blogService.getBlogByBlogId(blogId);
        AddBlogVo addBlogVo = new AddBlogVo();
        BeanUtils.copyProperties(blog,addBlogVo);
        Long[] list = blogTagService.getTagsOfOneBlog(blogId);
        addBlogVo.setValue(list);
        return Result.success().codeAndMessage(ResultInfo.SUCCESS).data(addBlogVo);
    }

    @PutMapping("/updateBlog/{blogIdToUpdate}")
    @LoginRequired
    public Result updateBlog(@PathVariable("blogIdToUpdate")Long blogId, @RequestBody AddBlogVo addBlogVo){

        blogService.updateBlogByBlogId(addBlogVo,blogId);

        return Result.success().codeAndMessage(ResultInfo.SUCCESS);

    }



}
