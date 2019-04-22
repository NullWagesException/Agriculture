package com.zf.service.impl;

import com.zf.mapper.ArticleMapper;
import com.zf.pojo.Article;
import com.zf.service.IArticleService;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class ArticleService implements IArticleService {

    @Setter
    private ArticleMapper articleMapper;

    @Override
    public List<Article> getSimpleMessage() {
        return articleMapper.getSimpleMessage();
    }

    @Override
    public List<Article> getAllPC() {
        return articleMapper.getAllPC();
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
