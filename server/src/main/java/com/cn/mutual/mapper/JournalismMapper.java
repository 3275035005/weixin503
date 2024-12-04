package com.cn.mutual.mapper;

import com.cn.mutual.entity.Journalism;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 资讯信息表 Mapper 接口
 * </p>
 */
public interface JournalismMapper extends BaseMapper<Journalism> {

    List<Journalism> pageQuery(Journalism data);
}
