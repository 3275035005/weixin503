package com.cn.mutual.mapper;

import com.cn.mutual.entity.Subscription;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 资讯订阅表 Mapper 接口
 * </p>
 */
public interface SubscriptionMapper extends BaseMapper<Subscription> {

    List<Subscription> getSubscriptionJournalismByUserId(@Param("userId") String userId, @Param("classifiedId") String classifiedId);
}
