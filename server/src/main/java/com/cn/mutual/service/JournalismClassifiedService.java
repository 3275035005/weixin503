package com.cn.mutual.service;

import com.cn.mutual.entity.JournalismClassified;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cn.mutual.utils.page.PageResult;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 */
public interface JournalismClassifiedService extends IService<JournalismClassified> {

    PageResult pageQuery(int page, int limit, JournalismClassified data);

    /**
     * 统计资讯分类的资讯数量
     * @return
     */
    List<JournalismClassified> getJournalismClassifiedBySumNumber();

}
