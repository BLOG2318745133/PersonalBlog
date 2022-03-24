package com.kun98.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kun98.common.po.BlogTag;

/**
 * @author LIU JIANKUN
 * @create 2022-03-20 15:15
 */
public interface BlogTagService extends IService<BlogTag> {

    int addTagsForOneBlog(long blogId, Long[] value);

    Long[] getTagsOfOneBlog(Long blogId);

    void removeAllTagsByBlogId(Long blogId);
}
