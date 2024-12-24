package com.anishan.dome.domain.dto;

import com.anishan.dome.annotation.SelectAlias;
import com.anishan.dome.domain.entity.BaseEntity;
import com.anishan.dome.domain.entity.Teacher;
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
 * 用户分表 教师表
 * @TableName teacher
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="teacher")
@Data
@ApiModel("教师表")
public class TeacherQuery extends BaseQueryParam<Teacher> implements Serializable {
    /**
     * 用户id
     */
    @SelectAlias("t")
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty("用户ID")
    private Long userId;

    /**
     * 职称
     */
    @SelectAlias("t")
    @ApiModelProperty("昵称")
    private String title;

    /**
     * 专业
     */
    @SelectAlias("t")
    @ApiModelProperty("专业")
    private Long majorId;


}