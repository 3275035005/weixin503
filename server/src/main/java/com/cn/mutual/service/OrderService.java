package com.cn.mutual.service;

import com.cn.mutual.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cn.mutual.utils.page.PageResult;

import java.util.List;

/**
 * <p>
 * 二手物品订单 服务类
 * </p>
 */
public interface OrderService extends IService<Order> {

    PageResult pageQuery(int page, int limit, Order data);

    List<Order> getOrderInfoList(String userId);

}
