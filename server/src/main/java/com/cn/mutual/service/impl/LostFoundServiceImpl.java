package com.cn.mutual.service.impl;

import com.cn.mutual.entity.LostFound;
import com.cn.mutual.entity.LostFound;
import com.cn.mutual.mapper.LostFoundMapper;
import com.cn.mutual.service.LostFoundService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.mutual.utils.page.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 失物招领 服务实现类
 * </p>
 */
@Service
public class LostFoundServiceImpl extends ServiceImpl<LostFoundMapper, LostFound> implements LostFoundService {

    @Override
    public PageResult pageQuery(int page, int limit, LostFound data) {
        PageHelper.startPage(page, limit);
        List<LostFound> queryList = baseMapper.pageQuery(data);
        PageInfo<LostFound> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }

    @Override
    public List<LostFound> getLostFoundList(String type, String classified) {
        return baseMapper.getLostFoundList(type, classified);
    }

    @Override
    public LostFound getLostFoundById(String id) {
        return baseMapper.getLostFoundById(id);
    }
}
