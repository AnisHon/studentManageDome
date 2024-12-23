package com.anishan.dome.domain.dto;

import com.anishan.dome.domain.entity.Instructor;
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
 * 用户分表 辅导员表
 * @TableName instructor
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("辅导员表")
public class InstructorQuery extends BaseQueryParam<Instructor> {

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("专业id")
    private Long majorId;

}