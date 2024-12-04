package com.cn.mutual.service;

import com.cn.mutual.entity.SeedStudent;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cn.mutual.entity.SysUser;

import java.util.List;

/**
 * <p>
 * 结实信息 服务类
 * </p>
 */
public interface SeedStudentService extends IService<SeedStudent> {

    List<SysUser> getThisStudentList(String userId);
    
}
