package com.anishan.dome.domain.dto;

import com.anishan.dome.enumeration.UserStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("更改状态")
public class StatusForm {

    @NotNull
    @ApiModelProperty("用户ID")
    private Long userId;

    @NotNull
    @ApiModelProperty("用户状态")
    private UserStatus status;

}
