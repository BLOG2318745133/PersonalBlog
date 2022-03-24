package com.kun98.common.vo;

import com.kun98.common.po.Type;
import lombok.Data;

/**
 * @author LIU JIANKUN
 * @create 2022-03-20 23:40
 */
@Data
public class TypeVO extends Type {

    /**
     * 每个分类对应的博客数量
     */
    Integer typeCount;
}
