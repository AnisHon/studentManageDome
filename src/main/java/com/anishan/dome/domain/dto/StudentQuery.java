package com.anishan.dome.domain.dto;

import cn.hutool.core.util.StrUtil;
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

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("班级id")
    private Long classId;

    @ApiModelProperty("专业")
    private Long majorId;

    @ApiModelProperty("姓名")
    private String username;

    @Override
    public LambdaQueryWrapper<Student> queryWrapper() {
        return new QueryWrapper<Student>()
                .eq(userId != null, "sys_user.user_id", userId)
                .eq(classId != null, "class.class_id", classId)
                .eq(majorId != null, "major.major_id", majorId)
                .eq(!StrUtil.isEmpty(username), "sys_user.username", username)
                .lambda();
    }
}