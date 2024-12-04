package com.cn.mutual.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 二手物品订单
 * </p>
 *
 * @author
 * @since 2024-01-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("`order`")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 商品id
     */
    private String goodsId;

    /**
     * 订单总价
     */
    private BigDecimal price;

    /**
     * 购买用户id
     */
    private String buyUserId;


    /**
     * 收货人电话
     */
    private String phone;
    /**
     * 收货人姓名
     */
    private String name;
    /**
     * 收货地址
     */
    private String address;
    /**
     * 购买时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;


    /**
     * 购买用户
     */
    @TableField(exist = false)
    private String userName;
    /**
     * 商品名称
     */
    @TableField(exist = false)
    private String goodsName;

    /**
     * 商品封面
     */
    @TableField(exist = false)
    private String image;
}
