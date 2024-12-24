package com.anishan.dome.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ScoreVo {

    @ApiModelProperty("学生ID")
    private Long studentId;

    @ApiModelProperty("课程ID")
    private Long courseId;

    @ApiModelProperty("任教课程ID")
    private Long teachId;

    @ApiModelProperty("班级ID")
    private Long classId;

    @ApiModelProperty("分数")
    private BigDecimal score;

    @ApiModelProperty("学分")
    private Long credit;

    @ApiModelProperty("学生名")
    private String studentName;

    @ApiModelProperty("课程名")
    private String courseName;

    @ApiModelProperty("学年")
    private Integer schoolYear;

}
