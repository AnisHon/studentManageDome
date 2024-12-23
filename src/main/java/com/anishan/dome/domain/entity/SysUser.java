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
import java.util.Date;

/**
 * 用户表
 * @TableName sys_user
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="sys_user")
@Data
@ApiModel("用户表")
public class SysUser extends BaseEntity implements Serializable {
    /**
     * 用户id
     */
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty("用户id")
    private Long userId;

    /**
     * 工号
     */
    @ApiModelProperty("工号")
    private String workNumber;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;

    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
    private String username;

    /**
     * 生日
     */
    @ApiModelProperty("生日")
    private Date birthday;

    /**
     * 性别 0 男 1女
     */
    @ApiModelProperty("性别 0 男 1女")
    private Integer gender;

    /**
     * (学生 1 辅导员 5 教师 10 管理员 20)
     */
    @ApiModelProperty("学生 1 辅导员 5 教师 10 管理员 20")
    private Integer role;

    /**
     * 用户状态(0 正常，1 异常)
     */
    @ApiModelProperty("用户状态(0 正常，1 异常)")
    private Integer status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}