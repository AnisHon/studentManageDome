package com.anishan.dome.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 选课表
 * @TableName student_course
 */
@TableName(value ="student_course")
@Data
@Accessors(chain = true)
@ApiModel("课程表")
public class StudentCourse implements Serializable {
    /**
     * 课程ID
     */
    @NotNull
    @ApiModelProperty("授课课程ID")
    private Long teachId;

    /**
     *
     */
    @NotNull
    @ApiModelProperty("学生id")
    private Long userId;

    /**
     *
     */
    @Min(message = "不是哥们",value = 0)
    @ApiModelProperty("得分")
    private BigDecimal score;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}