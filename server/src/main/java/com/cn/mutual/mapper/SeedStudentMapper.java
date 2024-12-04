package com.cn.mutual.mapper;

import com.cn.mutual.entity.SeedStudent;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cn.mutual.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 结实信息 Mapper 接口
 * </p>
 */
public interface SeedStudentMapper extends BaseMapper<SeedStudent> {

    List<SysUser> getThisStudentList(@Param("userId") String userId);
}
