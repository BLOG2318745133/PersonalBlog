package com.kun98.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kun98.common.po.BlogTag;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LIU JIANKUN
 * @create 2022-03-20 15:19
 */
@Repository
public interface BlogTagMapper extends BaseMapper<BlogTag> {


    @Select("SELECT t.tag_id " +
            "FROM blog_tag t " +
            "WHERE t.`blog_id` = #{blogId}")
    List<Long> getTags(Long blogId);
}
