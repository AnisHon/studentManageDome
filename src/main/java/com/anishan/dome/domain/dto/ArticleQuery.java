package com.anishan.dome.domain.dto;

import com.anishan.dome.domain.entity.Article;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("文章查询")
public class ArticleQuery extends BaseQueryParam<Article> {

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("时间")
    private String createTime;

}
