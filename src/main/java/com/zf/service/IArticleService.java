package com.zf.service;

import com.zf.pojo.Article;

import java.util.List;

public interface IArticleService {


    //<!--获取简略信息-->
    List<Article> getSimpleMessage();

    //<!--获取单个详细信息-->
    Article getMessage(Integer id);

    //<!--添加文章-->
    void insertArticle(Article article);

    //<!--删除文章-->
    void deleteArticle(Integer id);

    //<!--更新文章-->
    void updateArticle(Article article);

}
