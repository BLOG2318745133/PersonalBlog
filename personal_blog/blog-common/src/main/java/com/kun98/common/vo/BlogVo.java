package com.kun98.common.vo;

import com.kun98.common.po.Blog;
import lombok.Data;

/**
 * @author LIU JIANKUN
 * @create 2022-03-17 22:55
 */
@Data
public class BlogVo extends Blog {

    private String typeName;    //分类名称
    private String nickname;    //用户昵称
    private String avatar;      //用户头像

}
