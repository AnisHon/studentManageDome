package com.anishan.dome.domain.dto;

import com.anishan.dome.annotation.SelectAlias;
import com.anishan.dome.domain.entity.TeacherCourse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@ApiModel("选课查询表")
@Data

public class QueryEnrollCourse extends BaseQueryParam<TeacherCourse> {

    @SelectAlias("c")
    @ApiModelProperty("课程ID")
    private String courseId;

    @SelectAlias("c")
    @ApiModelProperty("课程名称")
    private String courseName;

    @SelectAlias("t")
    @ApiModelProperty("任课教师姓名")
    private String teacherName;

    @SelectAlias("m")
    @ApiModelProperty("学院名")
    private String institution;
}
