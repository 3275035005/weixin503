package com.cn.mutual.service;

import com.cn.mutual.entity.Notice;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cn.mutual.utils.page.PageResult;

/**
 * <p>
 * 通知公告信息表 服务类
 * </p>
 */
public interface NoticeService extends IService<Notice> {

    PageResult pageQuery(int page, int limit, Notice data);
}
