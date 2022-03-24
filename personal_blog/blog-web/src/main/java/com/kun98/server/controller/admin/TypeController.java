package com.kun98.server.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kun98.common.entity.QueryPageBean;
import com.kun98.common.entity.Result;
import com.kun98.common.entity.ResultInfo;
import com.kun98.common.po.Type;
import com.kun98.server.annotations.LoginRequired;
import com.kun98.server.service.TypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author LIU JIANKUN
 * @create 2022-03-17 22:47
 */
@Api(value = "后台管理-分类管理")
@RestController
@CrossOrigin
@RequestMapping("/types2")
@Slf4j
public class TypeController {

    @Autowired
    TypeService typeService;


    @LoginRequired
    @ApiOperation(value = "分页查询", notes = "返回分页数据")
    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean) {
        Page<Type> page = typeService.findPage(queryPageBean);
        return Result.success().codeAndMessage(ResultInfo.SUCCESS).data(page);
    }

    @PostMapping("/add")
    public Result addType(@RequestBody Type type) {
        boolean flag = typeService.addType(type);
        if(flag){
            return Result.success().codeAndMessage(ResultInfo.SUCCESS);
        }else{
            return Result.fail();
        }
    }

    @GetMapping("/getTypeList")
    public Result getTypeList() {
        List<Type> typeList = typeService.getTypeList();
        return Result.success().codeAndMessage(ResultInfo.SUCCESS).data(typeList);
    }

}
