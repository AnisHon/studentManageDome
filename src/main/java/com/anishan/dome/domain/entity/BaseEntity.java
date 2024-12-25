package com.anishan.dome.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.Version;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.annotations.Update;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.Null;
import java.time.LocalDateTime;

@Data
@ApiModel("实体类基类")
public class BaseEntity {

    @Null(groups = Update.class)
    @ApiModelProperty("插入和更新的时候请忽略，但是存在于查询结果")
    private LocalDateTime createTime;

    @Version
    @Null(groups = Update.class)
    @ApiModelProperty("插入和更新的时候请忽略，但是存在于查询结果")
    private LocalDateTime updateTime;


}
