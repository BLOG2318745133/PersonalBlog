package com.kun98.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kun98.common.entity.QueryPageBean;
import com.kun98.common.po.Blog;
import com.kun98.common.vo.BlogVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LIU JIANKUN
 * @create 2022-03-17 23:16
 */
@Repository
public interface BlogMapper extends BaseMapper<Blog> {

    @Select("SELECT b.blog_id, t.type_name, b.recommend, b.flag, b.update_time, b.title, b.flag " +
            "FROM blog b,type t " +
            "WHERE b.type_id = t.type_id AND b.uid = #{uid}" +
            " LIMIT #{start},#{pageSize} ")
    List<BlogVo> getAllBlogs(Long uid, Integer start, Integer pageSize);



    List<BlogVo> findHomePage(@Param("queryPageBean")QueryPageBean queryPageBean);

    BlogVo selectOneBloVoById(@Param("blog_id")Long blog_id);

    List<BlogVo> selectAllBlogVoByTypeId(@Param("queryPageBean")QueryPageBean queryPageBean);

    List<BlogVo> selectAllBlogVoByTagId(@Param("queryPageBean")QueryPageBean queryPageBean);

    List<BlogVo> getAllBlogsByUid(@Param("uid")Long uid, @Param("queryPageBean")QueryPageBean queryPageBean);
}
