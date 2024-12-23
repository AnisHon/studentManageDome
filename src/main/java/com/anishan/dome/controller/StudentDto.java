package com.anishan.dome.controller;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.annotations.Update;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class StudentDto {
    @NotNull(groups = Update.class)
    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("专业id")
    private Long majorId;

    @ApiModelProperty("班级id")
    private Long classId;

    /**
     * 工号
     */
    @NotNull
    @ApiModelProperty("工号")
    private String workNumber;

    /**
     * 密码
     */
    @NotNull
    @ApiModelProperty("密码")
    private String password;

    /**
     * 姓名
     */
    @NotNull
    @ApiModelProperty("姓名")
    private String username;

    /**
     * 生日
     */
    @NotNull
    @ApiModelProperty("生日")
    private Date birthday;

    /**
     * 性别 0 男 1女
     */
    @NotNull
    @ApiModelProperty("性别 0 男 1女")
    private Integer gender;

    /**
     * (学生 1 辅导员 5 教师 10 管理员 20)
     */
    @NotNull
    @ApiModelProperty("学生 1 辅导员 5 教师 10 管理员 20")
    private Integer role;

    /**
     * 用户状态(0 正常，1 异常)
     */
    @NotNull
    @ApiModelProperty("用户状态(0 正常，1 异常)")
    private Integer status;


}
