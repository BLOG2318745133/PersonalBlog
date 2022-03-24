package com.kun98.server.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kun98.common.entity.QueryPageBean;
import com.kun98.common.po.Type;
import com.kun98.common.vo.TypeVO;

import java.util.List;

/**
 * @author LIU JIANKUN
 * @create 2022-03-20 16:35
 */
public interface TypeService extends IService<Type> {
    Page<Type> findPage(QueryPageBean queryPageBean);

    boolean addType(Type type);

    List<Type> getTypeList();

    List<TypeVO> getTypeCount();
}
