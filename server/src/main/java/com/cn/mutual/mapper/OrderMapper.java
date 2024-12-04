package com.cn.mutual.mapper;

import com.cn.mutual.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 二手物品订单 Mapper 接口
 * </p>
 */
public interface OrderMapper extends BaseMapper<Order> {

    List<Order> pageQuery(Order data);


    List<Order> getOrderInfoList(@Param("userId") String userId);

}
