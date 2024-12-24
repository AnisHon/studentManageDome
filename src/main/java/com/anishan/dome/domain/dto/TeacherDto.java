package com.anishan.dome.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.annotations.Update;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
public class TeacherDto {
    @NotNull(groups = Update.class)
    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("职称")
    private String title;

    @ApiModelProperty("院系")
    private String institution;

    /**
     * 工号
     */
    @NotNull
    @NotEmpty
    @ApiModelProperty("工号")
    private String workNumber;

    /**
     * 密码
     */
    @NotNull
    @ApiModelProperty("密码")
    @Length(min = 8, max = 16)
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
     * 用户状态(0 正常，1 异常)
     */
    @NotNull
    @ApiModelProperty("用户状态(0 正常，1 异常)")
    private Integer status;
}
