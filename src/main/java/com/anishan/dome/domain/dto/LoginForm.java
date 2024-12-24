package com.anishan.dome.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("登录表单")
public class LoginForm {

    @ApiModelProperty("工号")
    private String workNumber;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("验证码token")
    private String token;

    @ApiModelProperty("验证码")
    private String code;

}
