package com.cn.mutual.mapper;

import com.cn.mutual.entity.JournalismClassified;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 */
public interface JournalismClassifiedMapper extends BaseMapper<JournalismClassified> {

    List<JournalismClassified> pageQuery(JournalismClassified data);

    List<JournalismClassified> getJournalismClassifiedBySumNumber();

}
