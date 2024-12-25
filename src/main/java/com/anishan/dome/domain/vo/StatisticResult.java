package com.anishan.dome.domain.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel("统计结果")
public class StatisticResult {

    @ExcelProperty("班级ID")
    @ApiModelProperty("班级ID")
    private Long classId;

    @ExcelProperty("教授课程ID")
    @ApiModelProperty("授课ID")
    private Long teachId;

    @ExcelProperty("班级名称ID")
    @ApiModelProperty("班级名称")
    private String className;

    @ExcelProperty("课程名称")
    @ApiModelProperty("课程名称")
    private String courseName;

    @ExcelProperty("平均分")
    @ApiModelProperty("平均分")
    private BigDecimal averageScore;

    @ExcelProperty("最高分")
    @ApiModelProperty("最高分")
    private BigDecimal maxScore;

    @ExcelProperty("最低分")
    @ApiModelProperty("最低分")
    private BigDecimal minScore;

    @ExcelProperty("及格人数")
    @ApiModelProperty("及格人数")
    private Integer pass;

    @ExcelProperty("不及格人数")
    @ApiModelProperty("不及格人数")
    private Integer fail;

}
