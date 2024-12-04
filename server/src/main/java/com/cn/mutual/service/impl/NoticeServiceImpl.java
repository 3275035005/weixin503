package com.cn.mutual.service.impl;

import com.cn.mutual.entity.Notice;
import com.cn.mutual.entity.Notice;
import com.cn.mutual.mapper.NoticeMapper;
import com.cn.mutual.service.NoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.mutual.utils.page.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 通知公告信息表 服务实现类
 * </p>
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

    @Override
    public PageResult pageQuery(int page, int limit, Notice data) {
        PageHelper.startPage(page, limit);
        List<Notice> queryList = baseMapper.pageQuery(data);
        PageInfo<Notice> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }
}
