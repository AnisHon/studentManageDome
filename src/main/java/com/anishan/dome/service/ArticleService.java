package com.anishan.dome.service;

import com.anishan.dome.domain.entity.Article;
import com.anishan.dome.domain.entity.ArticleDto;
import com.anishan.dome.domain.vo.ArticleVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author anishan
* @description 针对表【article(文章)】的数据库操作Service
* @createDate 2024-12-25 10:48:26
*/
public interface ArticleService extends IService<Article> {

    boolean saveArticle(ArticleDto entity);

    boolean updateArticle(ArticleDto entity);

    ArticleVo get(Long articleId);
}
