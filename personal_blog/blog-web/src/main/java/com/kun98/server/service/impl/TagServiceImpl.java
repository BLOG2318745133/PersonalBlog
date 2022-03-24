package com.kun98.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kun98.common.entity.QueryPageBean;
import com.kun98.common.po.Blog;
import com.kun98.common.po.BlogTag;
import com.kun98.common.po.Tag;
import com.kun98.common.po.Type;
import com.kun98.common.vo.TagVO;
import com.kun98.common.vo.TypeVO;
import com.kun98.server.mapper.BlogTagMapper;
import com.kun98.server.mapper.TagMapper;
import com.kun98.server.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LIU JIANKUN
 * @create 2022-03-20 16:00
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
    @Autowired
    TagMapper tagMapper;

    @Autowired
    BlogTagMapper blogTagMapper;

    @Override
    public Page<Tag> findPage(QueryPageBean queryPageBean) {
        //设置分页条件
        Page<Tag> page = new Page<>(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());

        QueryWrapper<Tag> wrapper = new QueryWrapper<>();

        return tagMapper.selectPage(page,wrapper);
    }

    @Override
    public List<Tag> getTagList() {
        return tagMapper.selectList(null);
    }

    @Override
    public List<TagVO> getTagCount() {
        List<Tag> tagList = getTagList();
        List<TagVO> returnList = new ArrayList<>();
        for(Tag tag : tagList){
            Integer count =blogTagMapper .selectCount(new QueryWrapper<BlogTag>().eq("tag_id", tag.getTagId()));
            TagVO tagVO = new TagVO();
            BeanUtils.copyProperties(tag,tagVO);
            tagVO.setTagCount(count);
            returnList.add(tagVO);
        }
        return returnList;
    }


}
