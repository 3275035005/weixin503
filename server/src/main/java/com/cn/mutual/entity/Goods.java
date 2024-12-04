package com.cn.mutual.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 二手物品
 * </p>
 *
 * @author
 * @since 2024-01-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 物品名称
     */
    private String name;

    /**
     * 物品介绍
     */
    private String content;

    /**
     * 发布用户id
     */
    private String userId;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 物品封面
     */
    private String image;

    /**
     * 物品状态(1正常 2已出售)
     */
    private String status;

    /**
     * 发布时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 发布用户
     */
    @TableField(exist = false)
    private String userName;

    /**
     * 头像
     */
    @TableField(exist = false)
    private String avatar;

}
