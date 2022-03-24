package com.kun98.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kun98.common.po.Views;
import com.kun98.server.mapper.ViewsMapper;
import com.kun98.server.service.ViewsService;
import org.springframework.stereotype.Service;

/**
 * @author LIU JIANKUN
 * @create 2022-03-17 23:19
 */
@Service
public class ViewsServiceImpl extends ServiceImpl<ViewsMapper, Views> implements ViewsService {

    @Override
    public void addViews(Long blogId, String host) {

    }
}
