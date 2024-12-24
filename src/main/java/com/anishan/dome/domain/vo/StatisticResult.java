package com.anishan.dome.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel("统计结果")
public class StatisticResult {

    @ApiModelProperty("班级ID")
    private Long classId;

    @ApiModelProperty("授课ID")
    private Long teachId;

    @ApiModelProperty("班级名称")
    private String className;

    @ApiModelProperty("课程ID")
    private String courseName;

    @ApiModelProperty("平均分")
    private BigDecimal averageScore;

    @ApiModelProperty("最高分")
    private BigDecimal maxScore;

    @ApiModelProperty("最低分")
    private BigDecimal minScore;

    @ApiModelProperty("及格人数")
    private Integer pass;

    @ApiModelProperty("不及格人数")
    private Integer fail;

}
