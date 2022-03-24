package com.kun98.server.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kun98.common.entity.QueryPageBean;
import com.kun98.common.entity.Result;
import com.kun98.common.entity.ResultInfo;
import com.kun98.common.po.Type;
import com.kun98.common.vo.BlogVo;
import com.kun98.server.service.BlogService;
import com.kun98.server.service.TypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author LIU JIANKUN
 * @create 2022-03-21 15:46
 */
@RequestMapping("/type")
@RestController
@Slf4j
@CrossOrigin
public class TypeShowController {

//    @Autowired
//    TypeService typeService;

    @Autowired
    BlogService blogService;

    @PostMapping("/getById")
    public Result getByTypeId(@RequestBody QueryPageBean queryPageBean){

        Page<BlogVo> page = blogService.getByTypeId(queryPageBean);

        return Result.success().codeAndMessage(ResultInfo.SUCCESS).data(page);
    }
}
