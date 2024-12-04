package com.cn.mutual.mapper;

import com.cn.mutual.entity.CarSharing;
import com.cn.mutual.entity.CarSharingInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 拼车用户明细信息 Mapper 接口
 * </p>
 */
public interface CarSharingInfoMapper extends BaseMapper<CarSharingInfo> {

    List<CarSharing> getCarSharingByUserIdList(String userId);
}
