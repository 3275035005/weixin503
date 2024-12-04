package com.cn.mutual.service.impl;

import com.cn.mutual.entity.Mutual;
import com.cn.mutual.mapper.MutualMapper;
import com.cn.mutual.service.MutualService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 互助信息 服务实现类
 * </p>
 */
@Service
public class MutualServiceImpl extends ServiceImpl<MutualMapper, Mutual> implements MutualService {

    @Override
    public List<Mutual> getMutualByUserIdList(String userId) {
        return baseMapper.getMutualByUserIdList(userId);
    }

    @Override
    public Mutual getMutualById(String id) {
        return baseMapper.getMutualById(id);
    }

    @Override
    public List<Mutual> mutualAndAvatarList() {
        return baseMapper.mutualAndAvatarList();
    }
}
