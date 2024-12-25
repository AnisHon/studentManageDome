package com.anishan.dome.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.annotations.Update;

import javax.validation.constraints.NotNull;

/**
 * 文章
 * @TableName article
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="article")
@Data
public class ArticleDto extends BaseEntity implements Serializable {


    @NotNull(groups = Update.class)
    private Long articleId;

    private Long userId;

    private String title;

    private String content;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}