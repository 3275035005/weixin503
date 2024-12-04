package com.cn.mutual.service;

import com.cn.mutual.entity.Subscription;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 资讯订阅表 服务类
 * </p>
 */
public interface SubscriptionService extends IService<Subscription> {

    /**
     * 查询我的订阅资讯
     * @param userId 用户id
     * @param classifiedId 资讯分类id
     * @return
     */
    List<Subscription> getSubscriptionJournalismByUserId(String userId, String classifiedId);

}
