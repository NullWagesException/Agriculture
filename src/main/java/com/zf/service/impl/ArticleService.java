package com.zf.service.impl;

import com.zf.mapper.ArticleMapper;
import com.zf.pojo.Article;
import com.zf.service.IArticleService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class ArticleService implements IArticleService {

    private ArticleMapper articleMapper;

    public void setArticleMapper(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    @Override
    public List<Article> getSimpleMessage() {
        return articleMapper.getSimpleMessage();
    }

    @Override
    public Article getMessage(Integer id) {
        return articleMapper.getMessage(id);
    }

    @Override
    public void insertArticle(Article article) {
        articleMapper.insertArticle(article);
    }

    @Override
    public void deleteArticle(Integer id) {
        articleMapper.deleteArticle(id);
    }

    @Override
    public void updateArticle(Article article) {
        articleMapper.updateArticle(article);
    }
}
