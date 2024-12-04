package com.cn.mutual.service.impl;

import com.cn.mutual.entity.Subscription;
import com.cn.mutual.mapper.SubscriptionMapper;
import com.cn.mutual.service.SubscriptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 资讯订阅表 服务实现类
 * </p>
 */
@Service
public class SubscriptionServiceImpl extends ServiceImpl<SubscriptionMapper, Subscription> implements SubscriptionService {

    @Override
    public List<Subscription> getSubscriptionJournalismByUserId(String userId, String classifiedId) {
        return baseMapper.getSubscriptionJournalismByUserId(userId, classifiedId);
    }

}
