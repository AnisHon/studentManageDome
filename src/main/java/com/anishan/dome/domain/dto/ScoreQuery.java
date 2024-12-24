package com.anishan.dome.domain.dto;

import com.anishan.dome.annotation.SelectAlias;
import com.anishan.dome.domain.entity.StudentCourse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("分数查询表单")
public class ScoreQuery extends BaseQueryParam<StudentCourse>{

    @SelectAlias("sc")
    @ApiModelProperty("学生ID")
    private Long userId;

    @SelectAlias("s")
    @ApiModelProperty("班级ID")
    private Long classId;

    @SelectAlias("su")
    @ApiModelProperty("学生名")
    private String username;

    @SelectAlias("c")
    @ApiModelProperty("课程名")
    private String courseName;

    @SelectAlias("c")
    @ApiModelProperty("学年")
    private Integer schoolYear;







}
