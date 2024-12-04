package com.cn.mutual.service.impl;

import com.cn.mutual.entity.JournalismClassified;
import com.cn.mutual.mapper.JournalismClassifiedMapper;
import com.cn.mutual.service.JournalismClassifiedService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.mutual.utils.page.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 */
@Service
public class JournalismClassifiedServiceImpl extends ServiceImpl<JournalismClassifiedMapper, JournalismClassified> implements JournalismClassifiedService {

    @Override
    public PageResult pageQuery(int page, int limit, JournalismClassified data) {
        PageHelper.startPage(page, limit);
        List<JournalismClassified> queryList = baseMapper.pageQuery(data);
        PageInfo<JournalismClassified> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }

    @Override
    public List<JournalismClassified> getJournalismClassifiedBySumNumber() {
        return baseMapper.getJournalismClassifiedBySumNumber();
    }
}
