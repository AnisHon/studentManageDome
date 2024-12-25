package com.anishan.dome.domain.dto;

import com.anishan.dome.annotation.SelectAlias;
import com.anishan.dome.domain.entity.StudentCourse;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class StatisticFrom extends BaseQueryParam<StudentCourse> {

    @ApiModelProperty("班级ID")
    private Long classId;

    @ApiModelProperty("任教课程ID，这个直接输入不用下拉框了")
    private Long teachId;



}
