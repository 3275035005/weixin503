package com.cn.mutual.mapper;

import com.cn.mutual.entity.LostFound;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 失物招领 Mapper 接口
 * </p>
 */
public interface LostFoundMapper extends BaseMapper<LostFound> {

    List<LostFound> pageQuery(LostFound data);

    List<LostFound> getLostFoundList(String type, String classified);

    LostFound getLostFoundById(@Param("id") String id);
}
