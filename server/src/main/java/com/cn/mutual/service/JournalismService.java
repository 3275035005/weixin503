package com.cn.mutual.service;

import com.cn.mutual.entity.Journalism;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cn.mutual.utils.page.PageResult;

/**
 * <p>
 * 资讯信息表 服务类
 * </p>
 */
public interface JournalismService extends IService<Journalism> {

    PageResult pageQuery(int page, int limit, Journalism data);
}
