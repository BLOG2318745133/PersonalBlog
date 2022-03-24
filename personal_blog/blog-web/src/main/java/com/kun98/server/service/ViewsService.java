package com.kun98.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kun98.common.po.Views;

/**
 * @author LIU JIANKUN
 * @create 2022-03-17 22:50
 */
public interface ViewsService extends IService<Views> {

    void addViews(Long blogId, String host);
}
