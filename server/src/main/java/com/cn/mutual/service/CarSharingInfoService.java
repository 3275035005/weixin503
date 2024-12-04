package com.cn.mutual.service;

import com.cn.mutual.entity.CarSharing;
import com.cn.mutual.entity.CarSharingInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 拼车用户明细信息 服务类
 * </p>
 */
public interface CarSharingInfoService extends IService<CarSharingInfo> {

    /**
     * 查询我的拼车
     * @param userId
     * @return
     */
    List<CarSharing> getCarSharingByUserIdList(String userId);

}
