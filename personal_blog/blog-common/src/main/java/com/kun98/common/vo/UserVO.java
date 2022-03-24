package com.kun98.common.vo;

import com.kun98.common.po.User;
import lombok.Data;

import java.io.Serializable;

/**
 * @author LIU JIANKUN
 * @create 2022-03-14 0:52
 */
@Data
public class UserVO extends User implements Serializable {

    private String verKey;  // 缓存在redis中的验证码的key
    private String code; // 登录时的验证码
}
