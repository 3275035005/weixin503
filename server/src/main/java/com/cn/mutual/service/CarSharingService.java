package com.cn.mutual.service;

import com.cn.mutual.entity.CarSharing;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 拼车信息 服务类
 * </p>
 */
public interface CarSharingService extends IService<CarSharing> {

    /**
     * 拼车详情查询
     * @param id
     * @return
     */
    CarSharing getCarSharingById(String id);
}
