package com.anishan.dome.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 用户分表 辅导员表
 * @TableName instructor
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="instructor")
@Data
@ApiModel("辅导员表")
public class Instructor extends BaseEntity implements Serializable {
    /**
     *
     */
    @ApiModelProperty("用户id")
    @TableId(type = IdType.ASSIGN_ID)
    private Long userId;

    /**
     *
     */
    @ApiModelProperty("专业id")
    private Long majorId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}