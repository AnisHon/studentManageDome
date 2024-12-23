package com.anishan.dome.domain.dto;


import com.anishan.dome.domain.entity.Course;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("课程查询")
public class CourseQuery extends BaseQueryParam<Course> {

    @ApiModelProperty("课程ID")
    private Long courseId;

    @ApiModelProperty("课程名称")
    private String courseName;

    @ApiModelProperty("学分")
    private Integer credit;

    @ApiModelProperty("课时")
    private Integer duration;

    @ApiModelProperty("学年")
    private Integer schoolYear;

}