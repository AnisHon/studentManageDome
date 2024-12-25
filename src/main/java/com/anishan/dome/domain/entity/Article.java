package com.anishan.dome.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文章
 * @TableName article
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="article")
@Data
public class Article extends BaseEntity implements Serializable {
    /**
     * 文章ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long articleId;

    /**
     * 文章作者ID
     */
    private Long userId;

    /**
     * 标题ID
     */
    private String title;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}