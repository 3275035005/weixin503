package com.cn.mutual.service;

import com.cn.mutual.entity.Mutual;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 互助信息 服务类
 * </p>
 */
public interface MutualService extends IService<Mutual> {

    List<Mutual> getMutualByUserIdList(String userId);

    Mutual getMutualById(String id);

    List<Mutual> mutualAndAvatarList();


}
