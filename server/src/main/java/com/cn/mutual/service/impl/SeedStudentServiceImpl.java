package com.cn.mutual.service.impl;

import com.cn.mutual.entity.SeedStudent;
import com.cn.mutual.entity.SysUser;
import com.cn.mutual.mapper.SeedStudentMapper;
import com.cn.mutual.service.SeedStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 结实信息 服务实现类
 * </p>
 */
@Service
public class SeedStudentServiceImpl extends ServiceImpl<SeedStudentMapper, SeedStudent> implements SeedStudentService {

    @Override
    public List<SysUser> getThisStudentList(String userId) {
        return baseMapper.getThisStudentList(userId);
    }
}
