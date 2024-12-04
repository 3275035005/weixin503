package com.cn.mutual.service.impl;

import com.cn.mutual.entity.Order;
import com.cn.mutual.entity.Order;
import com.cn.mutual.mapper.OrderMapper;
import com.cn.mutual.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.mutual.utils.page.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 二手物品订单 服务实现类
 * </p>
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Override
    public PageResult pageQuery(int page, int limit, Order data) {
        PageHelper.startPage(page, limit);
        List<Order> queryList = baseMapper.pageQuery(data);
        PageInfo<Order> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }

    @Override
    public List<Order> getOrderInfoList(String userId) {
        return baseMapper.getOrderInfoList(userId);
    }
}
