package com.cn.mutual.mapper;

import com.cn.mutual.entity.Mutual;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 互助信息 Mapper 接口
 * </p>
 */
public interface MutualMapper extends BaseMapper<Mutual> {

    List<Mutual> getMutualByUserIdList(@Param("userId") String userId);

    Mutual getMutualById(@Param("id")String id);

    List<Mutual> mutualAndAvatarList();


}
