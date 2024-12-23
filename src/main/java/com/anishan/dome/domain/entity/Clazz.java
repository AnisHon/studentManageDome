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
 * 班级表
 * @TableName class
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="class")
@Data
@ApiModel("班级")
public class Clazz extends BaseEntity implements Serializable {

    @ApiModelProperty("班级id")
    @TableId(type = IdType.ASSIGN_ID)
    private Long classId;

    @ApiModelProperty("专业ID")
    private Long majorId;

    @ApiModelProperty("班级姓名")
    private String className;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}