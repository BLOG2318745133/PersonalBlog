package com.kun98.common.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author LIU JIANKUN
 * @create 2022-03-17 22:51
 */
@Data
public class QueryPageBean {
    private Integer currentPage;    //页码
    private Integer pageSize;   //每页记录数
    private String queryString; //查询条件

    private Long typeId; //分类id
    private Long tagId; //标签id
}
