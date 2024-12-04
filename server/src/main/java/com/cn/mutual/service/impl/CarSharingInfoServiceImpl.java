package com.cn.mutual.service.impl;

import com.cn.mutual.entity.CarSharing;
import com.cn.mutual.entity.CarSharingInfo;
import com.cn.mutual.mapper.CarSharingInfoMapper;
import com.cn.mutual.service.CarSharingInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 拼车用户明细信息 服务实现类
 * </p>
 */
@Service
public class CarSharingInfoServiceImpl extends ServiceImpl<CarSharingInfoMapper, CarSharingInfo> implements CarSharingInfoService {

    @Override
    public List<CarSharing> getCarSharingByUserIdList(String userId) {
        return baseMapper.getCarSharingByUserIdList(userId);
    }
}
