package com.kun98.server.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kun98.common.entity.QueryPageBean;
import com.kun98.common.po.Tag;
import com.kun98.common.vo.TagVO;

import java.util.List;

/**
 * @author LIU JIANKUN
 * @create 2022-03-20 15:57
 */
public interface TagService extends IService<Tag> {
    Page<Tag> findPage(QueryPageBean queryPageBean);

    List<Tag> getTagList();

    List<TagVO> getTagCount();
}
