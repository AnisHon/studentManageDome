package com.anishan.dome.domain.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("标记Vo实体")
@Data
public class MarkVo {

    @ApiModelProperty("学生ID")
    private Long studentId;

    @ApiModelProperty("学生姓名")
    private String studentName;

    @ApiModelProperty("标记文字")
    private String tag;

    @ApiModelProperty("标记时间")
    private String createTime;

    @ApiModelProperty("更新时间")
    private String updateTime;

}
