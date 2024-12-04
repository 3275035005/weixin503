package com.cn.mutual.service.impl;

import com.cn.mutual.entity.SysUser;
import com.cn.mutual.mapper.SysUserMapper;
import com.cn.mutual.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.mutual.utils.page.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public PageResult pageQuery(int page, int limit, SysUser data) {
        PageHelper.startPage(page, limit);
        List<SysUser> queryList = baseMapper.pageQuery(data);
        PageInfo<SysUser> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }
}
