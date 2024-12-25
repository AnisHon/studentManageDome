package com.anishan.dome.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.anishan.dome.domain.entity.ArticleContent;
import com.anishan.dome.service.ArticleContentService;
import com.anishan.dome.mapper.ArticleContentMapper;
import org.springframework.stereotype.Service;

/**
* @author anishan
* @description 针对表【article_content(文章内容)】的数据库操作Service实现
* @createDate 2024-12-25 10:48:26
*/
@Service
public class ArticleContentServiceImpl extends ServiceImpl<ArticleContentMapper, ArticleContent>
    implements ArticleContentService{

}




