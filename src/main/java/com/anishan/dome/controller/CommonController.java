package com.anishan.dome.controller;

import com.anishan.dome.domain.AjaxResponse;
import com.anishan.dome.domain.entity.Article;
import com.anishan.dome.domain.entity.BaseEntity;
import com.anishan.dome.domain.vo.ArticleVo;
import com.anishan.dome.service.ArticleService;
import com.anishan.dome.utils.AuthUtils;
import com.anishan.dome.utils.SchoolYearUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@ApiModel("通用")
@RequestMapping("/common")
public class CommonController {

    private final ArticleService articleService;
    private final SchoolYearUtil schoolYearUtil;

    @ApiOperation("limit")
    @GetMapping("/notifications/{limit}")
    public AjaxResponse<List<Article>> listAjaxResponse(@PathVariable Long limit) {

        Page<Article> page = Page.of(1, limit);
        page.addOrder(new OrderItem().setAsc(false).setColumn("update_time"));


        Page<Article> page1 = articleService.page(page);
        List<Article> records = page1.getRecords();
        return AjaxResponse.ok(records);
    }

    @ApiOperation("")
    @GetMapping("/notification/{articleId}")
    public AjaxResponse<ArticleVo> getArticle(@PathVariable Long articleId) {
        ArticleVo articleVo = articleService.get(articleId);
        return AjaxResponse.ok(articleVo);
    }

    @ApiOperation("是否开启选课")
    @GetMapping("/isStarted")
    public AjaxResponse<Boolean> isStarted(@PathVariable Integer articleId) {
        return AjaxResponse.ok(schoolYearUtil.isStarted());
    }

}
