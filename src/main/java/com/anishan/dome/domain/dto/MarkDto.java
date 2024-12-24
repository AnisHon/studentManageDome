package com.anishan.dome.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("标记学生表单")
public class MarkDto {

    @NotNull
    @ApiModelProperty("学生ID")
    private Long studentId;

    @ApiModelProperty("标记内容")
    private String tag;

}
