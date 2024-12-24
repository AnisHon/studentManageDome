package com.anishan.dome.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.annotations.Update;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@ApiModel("辅导员表单")
public class InstructorDto {
    @NotNull(groups = Update.class)
    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("专业id")
    private Long majorId;


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
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,16}$\n", message = "密码必须包含数字和字母，长度6-16")
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
