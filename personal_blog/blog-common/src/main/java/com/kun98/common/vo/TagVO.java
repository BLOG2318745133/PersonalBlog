package com.kun98.common.vo;

import com.kun98.common.po.Tag;
import lombok.Data;

/**
 * @author LIU JIANKUN
 * @create 2022-03-20 23:50
 */
@Data
public class TagVO extends Tag {

    /**
     * 每个标签对应的博客数量
     */
    Integer tagCount;
}
