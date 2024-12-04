package com.cn.mutual.service.impl;

import com.cn.mutual.entity.Goods;
import com.cn.mutual.entity.Goods;
import com.cn.mutual.mapper.GoodsMapper;
import com.cn.mutual.service.GoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.mutual.utils.page.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 二手物品 服务实现类
 * </p>
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Override
    public PageResult pageQuery(int page, int limit, Goods data) {
        PageHelper.startPage(page, limit);
        List<Goods> queryList = baseMapper.pageQuery(data);
        PageInfo<Goods> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }

    @Override
    public List<Goods> getGoodsList(String status, String userId) {
        return baseMapper.getGoodsList(status, userId);
    }
}
