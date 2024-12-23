package com.anishan.dome.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("选课实体类信息")
public class EnrollCourse {

    @ApiModelProperty("课程ID")
    private String courseId;

    @ApiModelProperty("课程名称")
    private String courseName;

    @ApiModelProperty("学分")
    private Integer credit;

    @ApiModelProperty("课时")
    private Integer duration;

    @ApiModelProperty("学年")
    private Integer schoolYear;

    @ApiModelProperty("任课教师姓名")
    private String teacherName;

    @ApiModelProperty("学院名")
    private String institution;

}
