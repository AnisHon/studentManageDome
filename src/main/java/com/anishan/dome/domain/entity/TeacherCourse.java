package com.anishan.dome.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 任教表
 * @TableName teacher_course
 */
@TableName(value ="teacher_course")
@Data
public class TeacherCourse implements Serializable {

    @ApiModelProperty("任教课程ID")
    private Long teachId;

    @ApiModelProperty("课程ID")
    private Long courseId;

    /**
     *
     */
    @ApiModelProperty("教师id")
    private Long userId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}