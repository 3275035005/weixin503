package com.cn.mutual.mapper;

import com.cn.mutual.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 二手物品 Mapper 接口
 * </p>
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    List<Goods> pageQuery(Goods data);

    List<Goods> getGoodsList(@Param("status") String status,@Param("userId") String userId);
}
