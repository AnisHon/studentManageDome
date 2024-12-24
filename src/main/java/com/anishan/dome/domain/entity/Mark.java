package com.anishan.dome.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 标记表
 * @TableName mark
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="mark")
@Data
@ApiModel("标记表")
@Accessors(chain = true)
public class Mark extends BaseEntity implements Serializable {
    /**
     *
     */
    @ApiModelProperty("学生ID")
    private Long studentId;

    /**
     *
     */
    @ApiModelProperty("辅导员ID")
    private Long instructorId;

    /**
     *
     */
    @ApiModelProperty("标记")
    private String tag;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}