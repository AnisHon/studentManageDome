package com.anishan.dome.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import springfox.documentation.annotations.ApiIgnore;

import java.time.LocalDateTime;

@Data
@ApiModel("实体类基类")
public class BaseEntity {


    @ApiModelProperty("插入和更新的时候请忽略，但是存在于查询结果")
    private LocalDateTime createTime;
    @ApiModelProperty("插入和更新的时候请忽略，但是存在于查询结果")
    private LocalDateTime updateTime;


}
