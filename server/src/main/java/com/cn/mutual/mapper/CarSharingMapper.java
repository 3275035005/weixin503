package com.cn.mutual.mapper;

import com.cn.mutual.entity.CarSharing;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 拼车信息 Mapper 接口
 * </p>
 */
public interface CarSharingMapper extends BaseMapper<CarSharing> {

    CarSharing getCarSharingById(String id);

}
