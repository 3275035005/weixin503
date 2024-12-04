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
 * 资讯信息表
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Journalism implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 资讯标题
     */
    private String title;

    /**
     * 资讯封面
     */
    private String image;

    /**
     * 资讯内容
     */
    private String content;

    /**
     * 资讯分类id
     */
    private String classifiedId;

    /**
     * 发布时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;


    /**
     * 资讯分类名称
     */
    @TableField(exist = false)
    private String classifiedName;

    /**
     * 订阅id
     */
    @TableField(exist = false)
    private String subscriptionId;
}
