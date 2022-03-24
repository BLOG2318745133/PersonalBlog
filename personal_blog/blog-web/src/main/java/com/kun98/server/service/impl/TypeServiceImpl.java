package com.kun98.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kun98.common.entity.QueryPageBean;
import com.kun98.common.po.Blog;
import com.kun98.common.po.Type;
import com.kun98.common.vo.TypeVO;
import com.kun98.server.mapper.BlogMapper;
import com.kun98.server.mapper.TypeMapper;
import com.kun98.server.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LIU JIANKUN
 * @create 2022-03-20 16:39
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {

    @Autowired
    TypeMapper typeMapper;

    @Autowired
    BlogMapper blogMapper;

    @Override
    public Page<Type> findPage(QueryPageBean queryPageBean) {
        Page<Type> page = new Page<>(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        QueryWrapper<Type> wrapper = new QueryWrapper<>();
        return typeMapper.selectPage(page,wrapper);
    }

    @Override
    public boolean addType(Type type) {
        int insert = typeMapper.insert(type);
        return insert > 0;
    }

    @Override
    public List<Type> getTypeList() {
        return typeMapper.selectList(null);
    }

    @Override
    public List<TypeVO> getTypeCount() {
        List<Type> typeList = typeMapper.selectList(null);
        List<TypeVO> returnList = new ArrayList<>();
        for(Type type : typeList){
            Integer count = blogMapper.selectCount(new QueryWrapper<Blog>().eq("type_id", type.getTypeId()));
            TypeVO typeVO = new TypeVO();
            BeanUtils.copyProperties(type,typeVO);
            typeVO.setTypeCount(count);
            returnList.add(typeVO);
        }
        return returnList;
    }
}
