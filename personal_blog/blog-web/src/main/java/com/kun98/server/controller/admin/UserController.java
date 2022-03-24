package com.kun98.server.controller.admin;

import com.kun98.common.entity.Result;
import com.kun98.common.entity.ResultInfo;
import com.kun98.common.po.User;
import com.kun98.server.annotations.LoginRequired;
import com.kun98.server.service.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author LIU JIANKUN
 * @create 2022-03-16 15:40
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
@Api("后台管理-用户管理")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 注册新用户接口
     * @param user
     * @return
     */
    @PostMapping("/add")
    public Result addUser(@RequestBody User user){
        userService.add(user);
        return Result.success().codeAndMessage(ResultInfo.SUCCESS);
    }

    @PutMapping("/updateUser")
    @LoginRequired
    public Result updateUser(@RequestBody User user){
        User updateUser = userService.updateUser(user);
        return updateUser != null ?Result.success().codeAndMessage(ResultInfo.SUCCESS).data(updateUser) : Result.fail();
    }

    @GetMapping("/getUserList")
    public Result getUserList(){
        List<User> userList = userService.getUserList();
        return Result.success().codeAndMessage(ResultInfo.SUCCESS).data(userList);
    }


}
