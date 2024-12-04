package com.cn.mutual.service;

import com.cn.mutual.entity.LostFound;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cn.mutual.utils.page.PageResult;

import java.util.List;

/**
 * <p>
 * 失物招领 服务类
 * </p>
 */
public interface LostFoundService extends IService<LostFound> {

    PageResult pageQuery(int page, int limit, LostFound data);

    List<LostFound> getLostFoundList(String type, String classified);

    LostFound getLostFoundById(String id);
}
