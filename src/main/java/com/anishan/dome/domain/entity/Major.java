package com.anishan.dome.domain.entity;

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
 * 专业表
 * @TableName major
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="major")
@Data
@ApiModel("专业表")
public class Major extends BaseEntity implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty("专业id")
    private Long majorId;

    /**
     *
     */
    @ApiModelProperty("专业名称")
    private String majorName;

    /**
     *
     */
    @ApiModelProperty("学院名称")
    private String institution;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}