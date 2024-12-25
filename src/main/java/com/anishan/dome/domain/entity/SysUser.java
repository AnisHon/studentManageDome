package com.anishan.dome.domain.entity;

import com.anishan.dome.enumeration.Gender;
import com.anishan.dome.enumeration.RoleEnum;
import com.anishan.dome.enumeration.UserStatus;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.annotations.Update;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
    @NotNull(groups = Update.class)
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty("用户id")
    private Long userId;

    /**
     * 工号
     */
    @NotBlank(message = "工号不能为空")
    @ApiModelProperty("工号")
    private String workNumber;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    @NotNull
    @Length(min = 8, max = 16)
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
    private Gender gender;

    /**
     * (学生 1 辅导员 5 教师 10 管理员 20)
     */
    @ApiModelProperty("学生 1 辅导员 5 教师 10 管理员 20")
    private RoleEnum role;

    @ApiModelProperty("头像")
    private String portrait;
    /**
     * 用户状态(0 正常，1 异常)
     */
    @ApiModelProperty("用户状态(0 正常，1 异常)")
    private UserStatus status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}