package com.cn.mutual.service.impl;

import com.cn.mutual.entity.CarSharing;
import com.cn.mutual.mapper.CarSharingMapper;
import com.cn.mutual.service.CarSharingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 拼车信息 服务实现类
 * </p>
 */
@Service
public class CarSharingServiceImpl extends ServiceImpl<CarSharingMapper, CarSharing> implements CarSharingService {

    @Override
    public CarSharing getCarSharingById(String id) {
        CarSharing carSharing = baseMapper.getCarSharingById(id);
        carSharing.setSeat(carSharing.getSeat() - carSharing.getUserList().size());
        return carSharing;
    }
}
