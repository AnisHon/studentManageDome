package com.anishan.dome.domain.dto;

import cn.hutool.core.util.StrUtil;
import com.anishan.dome.annotation.SelectAlias;
import com.anishan.dome.domain.entity.BaseEntity;
import com.anishan.dome.domain.entity.Student;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

    @SelectAlias("st")
    @ApiModelProperty("用户id")
    private Long userId;

    @SelectAlias("st")
    @ApiModelProperty("班级id")
    private Long classId;

    @SelectAlias("c")
    @ApiModelProperty("专业")
    private Long majorId;

    @SelectAlias("u")
    @ApiModelProperty("学号")
    private String workNumber;

    @SelectAlias("u")
    @ApiModelProperty("姓名")
    private String username;

}