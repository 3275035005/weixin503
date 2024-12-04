package com.cn.mutual.mapper;

import com.cn.mutual.entity.Notice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 通知公告信息表 Mapper 接口
 * </p>
 */
public interface NoticeMapper extends BaseMapper<Notice> {

    List<Notice> pageQuery(Notice data);

}
