package com.cn.mutual.service;

import com.cn.mutual.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cn.mutual.utils.page.PageResult;

/**
 * <p>
 * 用户表 服务类
 * </p>
 */
public interface SysUserService extends IService<SysUser> {

    PageResult pageQuery(int page, int limit, SysUser data);
}
