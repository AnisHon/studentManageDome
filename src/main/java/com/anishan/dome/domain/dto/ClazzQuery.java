package com.anishan.dome.domain.dto;

import com.anishan.dome.domain.entity.Clazz;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 班级表
 * @TableName class
 */

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("班级")
public class ClazzQuery extends BaseQueryParam<Clazz> {

    @ApiModelProperty("班级id")
    private Long classId;

    @ApiModelProperty("专业ID")
    private Long majorId;

    @ApiModelProperty("班级姓名")
    private String className;

}