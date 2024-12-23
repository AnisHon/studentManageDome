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
 * 用户分表 学生表
 * @TableName student
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="student")
@Data
@ApiModel("学生表")
public class Student extends BaseEntity implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty("用户id")
    private Long userId;

    /**
     *
     */
    @ApiModelProperty("专业id")
    private Long majorId;

    /**
     *
     */
    @ApiModelProperty("班级id")
    private Long classId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}