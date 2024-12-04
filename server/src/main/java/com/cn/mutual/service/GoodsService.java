package com.cn.mutual.service;

import com.cn.mutual.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cn.mutual.utils.page.PageResult;

import java.util.List;

/**
 * <p>
 * 二手物品 服务类
 * </p>
 */
public interface GoodsService extends IService<Goods> {

    PageResult pageQuery(int page, int limit, Goods data);

    List<Goods> getGoodsList(String status, String userId);
}
