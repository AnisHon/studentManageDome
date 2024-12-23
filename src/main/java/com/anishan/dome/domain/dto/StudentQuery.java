package com.anishan.dome.domain.dto;

import com.anishan.dome.domain.entity.BaseEntity;
import com.anishan.dome.domain.entity.Student;
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
public class StudentQuery extends BaseQueryParam<Student> {

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("专业id")
    private Long majorId;

    @ApiModelProperty("班级id")
    private Long classId;

}