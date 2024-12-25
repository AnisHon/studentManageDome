package com.anishan.dome.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 文章内容
 * @TableName article_content
 */
@TableName(value ="article_content")
@Data
public class ArticleContent implements Serializable {
    /**
     * 文章内容ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long articleId;

    /**
     * 文章内容
     */
    private String content;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}