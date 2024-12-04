package com.cn.mutual.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 失物招领
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LostFound implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 发布用户
     */
    private String userId;

    /**
     * 物品标题
     */
    private String title;

    /**
     * 物品类型（0证件 1服饰 2数码 3日用品 4其它）
     */
    private String classified;

    /**
     * 认领地址
     */
    private String address;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 备注
     */
    private String remark;

    /**
     * 封面
     */
    private String image;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 状态（0认领中 1认领成功）
     */
    private String status;

    /**
     * 类型(0失物招领 1寻物启事)
     */
    private String type;

    @TableField(exist = false)
    private String userName;


    @TableField(exist = false)
    private String avatar;
}
