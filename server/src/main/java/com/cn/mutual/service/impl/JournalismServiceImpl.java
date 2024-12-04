package com.cn.mutual.service.impl;

import com.cn.mutual.entity.Journalism;
import com.cn.mutual.mapper.JournalismMapper;
import com.cn.mutual.service.JournalismService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.mutual.utils.page.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 资讯信息表 服务实现类
 * </p>
 */
@Service
public class JournalismServiceImpl extends ServiceImpl<JournalismMapper, Journalism> implements JournalismService {

    @Override
    public PageResult pageQuery(int page, int limit, Journalism data) {
        PageHelper.startPage(page, limit);
        List<Journalism> queryList = baseMapper.pageQuery(data);
        PageInfo<Journalism> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }
}
