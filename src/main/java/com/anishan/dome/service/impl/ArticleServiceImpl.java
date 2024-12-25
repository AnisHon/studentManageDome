package com.anishan.dome.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.anishan.dome.domain.entity.ArticleContent;
import com.anishan.dome.domain.entity.ArticleDto;
import com.anishan.dome.domain.vo.ArticleVo;
import com.anishan.dome.service.ArticleContentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.anishan.dome.domain.entity.Article;
import com.anishan.dome.service.ArticleService;
import com.anishan.dome.mapper.ArticleMapper;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
* @author anishan
* @description 针对表【article(文章)】的数据库操作Service实现
* @createDate 2024-12-25 10:48:26
*/
@Service
@Slf4j
@RequiredArgsConstructor
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{

    private final ArticleContentService articleContentService;


    @Override
    public boolean saveArticle(ArticleDto entity) {
        Article article = BeanUtil.copyProperties(entity, Article.class);
        ArticleContent articleContent = BeanUtil.copyProperties(entity, ArticleContent.class);


        boolean save = this.save(article);

        articleContent.setArticleId(article.getArticleId());
        save &= Db.save(articleContent);


        return save;
    }

    @Override
    public boolean updateArticle(ArticleDto entity) {
        Article article = BeanUtil.copyProperties(entity, Article.class);
        ArticleContent articleContent = BeanUtil.copyProperties(entity, ArticleContent.class);

        boolean update = this.updateById(article);

        articleContent.setArticleId(article.getArticleId());
        update &= Db.updateById(articleContent);

        return update;
    }

    @Override
    public ArticleVo get(Long articleId) {
        Article article = this.getById(articleId);
        ArticleContent articleContent = articleContentService.getById(articleId);

        System.out.println(article);

        ArticleVo articleVo = BeanUtil.copyProperties(article, ArticleVo.class);

        if (articleContent != null) {
            articleVo.setContent(articleContent.getContent());
        }

        return articleVo;
    }
}




