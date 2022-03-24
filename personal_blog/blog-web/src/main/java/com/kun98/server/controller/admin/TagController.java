package com.kun98.server.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kun98.common.entity.QueryPageBean;
import com.kun98.common.entity.Result;
import com.kun98.common.entity.ResultInfo;
import com.kun98.common.po.Tag;
import com.kun98.server.annotations.LoginRequired;
import com.kun98.server.service.TagService;
import com.netflix.discovery.converters.Auto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LIU JIANKUN
 * @create 2022-03-17 22:47
 */
@Api(value = "后台管理-标签管理")
@RestController
@CrossOrigin
@RequestMapping("/tag")
@Slf4j
public class TagController {

    @Autowired
    private TagService tagService;

    @LoginRequired
    @ApiOperation(value = "分页查询", notes = "返回分页数据")
    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean) {
        Page<Tag> page = tagService.findPage(queryPageBean);
        return Result.success().codeAndMessage(ResultInfo.SUCCESS).data(page);
    }

    @GetMapping("/getTagList")
    public Result getTagList(){
        List<Tag> tagList = tagService.getTagList();
        return Result.success().codeAndMessage(ResultInfo.SUCCESS).data(tagList);
    }




}
