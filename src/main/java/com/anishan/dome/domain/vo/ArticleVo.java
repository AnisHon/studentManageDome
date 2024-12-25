package com.anishan.dome.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ArticleVo {

    @TableId(type = IdType.ASSIGN_ID)
    private Long articleId;

    private Long userId;

    private String title;

    private String content;

}
