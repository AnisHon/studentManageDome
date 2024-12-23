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
 *
 * @TableName course
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="course")
@Data
@ApiModel("课程表")
public class Course extends BaseEntity implements Serializable {
    /**
     *
     */
    @ApiModelProperty("课程ID")
    @TableId(type = IdType.ASSIGN_ID)
    private Long courseId;

    /**
     *
     */
    @ApiModelProperty("课程名称")
    private String courseName;

    /**
     *
     */
    @ApiModelProperty("学分")
    private Integer credit;

    /**
     *
     */
    @ApiModelProperty("课时")
    private Integer duration;

    /**
     *
     */
    @ApiModelProperty("学年")
    private Integer schoolYear;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}