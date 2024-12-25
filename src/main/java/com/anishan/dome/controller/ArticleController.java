package com.anishan.dome.controller;


import com.anishan.dome.domain.AjaxResponse;
import com.anishan.dome.domain.dto.ArticleQuery;
import com.anishan.dome.domain.entity.Article;
import com.anishan.dome.domain.entity.ArticleDto;
import com.anishan.dome.domain.vo.ArticleVo;
import com.anishan.dome.domain.vo.PageResponse;
import com.anishan.dome.service.ArticleService;
import com.anishan.dome.utils.AuthUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/system/article")
@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;


    @GetMapping("/{articleId}")
    @ResponseBody
    public AjaxResponse<ArticleVo> get(@PathVariable Long articleId) {


        return AjaxResponse.ok(articleService.get(articleId));
    }

    @DeleteMapping("/{articleId}")
    @ResponseBody
    public AjaxResponse<Void> delete(@PathVariable List<Long> articleId) {
        articleService.removeByIds(articleId);
        return AjaxResponse.ok(null);
    }

    @GetMapping("/list")
    public AjaxResponse<PageResponse<Article>> list(@Validated ArticleQuery articleQuery) {
        Page<Article> page = articleQuery.queryPage();
        LambdaQueryWrapper<Article> wrapper = articleQuery.queryWrapper();

        page = articleService.page(page, wrapper);

        return AjaxResponse.ok(PageResponse.fromPage(page));
    }

    @PostMapping
    @ResponseBody
    public AjaxResponse<Boolean> save(@RequestBody ArticleDto entity) {
        entity.setUserId(AuthUtils.getUserId());
        return AjaxResponse.ok(articleService.saveArticle(entity));
    }

    @PutMapping
    @ResponseBody
    public AjaxResponse<Boolean> update(@RequestBody @Validated(Update.class) ArticleDto entity) {
        entity.setUserId(AuthUtils.getUserId());
        return AjaxResponse.ok(articleService.updateArticle(entity));
    }
}
