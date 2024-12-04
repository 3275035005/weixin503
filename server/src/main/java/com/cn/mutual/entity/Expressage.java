package com.cn.mutual.entity;

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
 * 快递信息
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Expressage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 领取地点
     */
    private String startAddress;

    /**
     * 收货地点
     */
    private String endAddress;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 快递类型（1小件 2中件 3大件）
     */
    private String type;

    /**
     * 发布人
     */
    private String userId;

    /**
     * 状态 （0等待领取 1领取成功）
     */
    private String status;

    /**
     * 领取用户id
     */
    private String goodsUserId;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
}
