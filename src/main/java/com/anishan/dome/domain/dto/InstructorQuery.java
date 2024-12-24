package com.anishan.dome.domain.dto;

import com.anishan.dome.annotation.SelectAlias;
import com.anishan.dome.domain.entity.Instructor;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * 用户分表 辅导员表
 * @TableName instructor
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@ApiModel("辅导员表")
public class InstructorQuery extends BaseQueryParam<Instructor> {

    @SelectAlias("ins")
    @ApiModelProperty("用户id")
    private Long userId;

    @SelectAlias("ins")
    @ApiModelProperty("专业")
    private Long majorId;

    @SelectAlias("u")
    @ApiModelProperty("姓名")
    private String username;

}