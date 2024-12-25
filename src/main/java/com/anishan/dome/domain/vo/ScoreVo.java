package com.anishan.dome.domain.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ScoreVo {

    @ExcelProperty("学生ID")
    @ApiModelProperty("学生ID")
    private Long studentId;

    @ExcelProperty("课程ID")
    @ApiModelProperty("课程ID")
    private Long courseId;

    @ExcelProperty("任教课程ID")
    @ApiModelProperty("任教课程ID")
    private Long teachId;

    @ExcelProperty("班级ID")
    @ApiModelProperty("班级ID")
    private Long classId;

    @ExcelProperty("分数")
    @ApiModelProperty("分数")
    private BigDecimal score;

    @ExcelProperty("学分")
    @ApiModelProperty("学分")
    private Long credit;

    @ExcelProperty("学生名")
    @ApiModelProperty("学生名")
    private String studentName;

    @ExcelProperty("课程名")
    @ApiModelProperty("课程名")
    private String courseName;

    @ExcelProperty("教师名")
    @ApiModelProperty("教师名")
    private String teacherName;

    @ExcelProperty("学年")
    @ApiModelProperty("学年")
    private Integer schoolYear;

}
