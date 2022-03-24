package com.kun98.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kun98.common.po.BlogTag;
import com.kun98.server.mapper.BlogTagMapper;
import com.kun98.server.service.BlogTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author LIU JIANKUN
 * @create 2022-03-20 15:15
 */
@Service
public class BlogTagServiceImpl extends ServiceImpl<BlogTagMapper, BlogTag> implements BlogTagService {
    @Override
    public void removeAllTagsByBlogId(Long blogId) {
        blogTagMapper.delete(new QueryWrapper<BlogTag>().eq("blog_id",blogId));
    }

    @Autowired
    BlogTagMapper blogTagMapper;

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    public int addTagsForOneBlog(long blogId, Long[] value) {
        BlogTag blogTag = new BlogTag();
        blogTag.setBlogId(blogId);
        int all = 0;
        for(Long tagId: value){
            blogTag.setTagId(tagId);
            int insert = blogTagMapper.insert(blogTag);
            all += insert;
        }
        return all;
    }

    @Override
    public Long[] getTagsOfOneBlog(Long blogId) {
        List<Long> tags = blogTagMapper.getTags(blogId);
        return tags.toArray(tags.toArray(new Long[0]));
    }
}
