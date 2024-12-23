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
 * 用户分表 教师表
 * @TableName teacher
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="teacher")
@Data
@ApiModel("教师表")
public class Teacher extends BaseEntity implements Serializable {
    /**
     * 用户id
     */
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty("用户ID")
    private Long userId;

    /**
     * 职称
     */
    @ApiModelProperty("昵称")
    private String title;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}