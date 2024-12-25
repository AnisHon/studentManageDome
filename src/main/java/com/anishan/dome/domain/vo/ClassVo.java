package com.anishan.dome.domain.vo;

import com.anishan.dome.domain.entity.Major;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel("班级VO")
public class ClassVo {

    @ApiModelProperty("班级ID")
    private Long classId;

    @ApiModelProperty("名称ID")
    private String className;


    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    @ApiModelProperty("学院ID")
    private Major major;

}
