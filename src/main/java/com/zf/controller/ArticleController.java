package com.zf.controller;

import com.alibaba.fastjson.JSON;
import com.zf.pojo.Article;
import com.zf.service.IArticleService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("article")
public class ArticleController extends BaseC {

    @Setter
    @Autowired
    private IArticleService articleService;

    //获取简略信息
    @RequestMapping("getAll")
    @ResponseBody
    public void getSimpleMessage(HttpServletResponse response){
        List<Article> simpleMessage = articleService.getSimpleMessage();
        String str = JSON.toJSONString(simpleMessage);
        ajaxReturn(true,str,response);
    }

    //根据ID获取某个信息
    @RequestMapping("get")
    @ResponseBody
    public void getMessage(HttpServletResponse response,Integer id){
        Article message = articleService.getMessage(id);
        String str = JSON.toJSONString(message);
        ajaxReturn(true,str,response);
    }

//   添加文章
    @RequestMapping("insert")
    @ResponseBody
    public void insertArticle(HttpServletResponse response,Article article){
        article.setTime(new Date());
        try{
            articleService.insertArticle(article);
            ajaxReturn(true,"添加成功",response);
        }catch (Exception e){
            ajaxReturn(false,"添加失败",response);
        }
    }

//    删除文章
    @RequestMapping("delete")
    @ResponseBody
    public void deleteArticle(HttpServletResponse response,Integer id){
        try{
            articleService.deleteArticle(id);
            ajaxReturn(true,"删除成功",response);
        }catch (Exception e){
            ajaxReturn(false,"删除失败",response);
        }
    }

//    更新文章
    @RequestMapping("update")
    @ResponseBody
    public void updateArticle(HttpServletResponse response,Article article){
        Article article_r = articleService.getMessage(article.getId());
        if (article.getTitle() != null)
            article_r.setTitle(article.getTitle());
        if (article.getImagepath() != null)
            article_r.setImagepath(article.getImagepath());
        if (article.getText() != null)
            article_r.setText(article.getText());
        article_r.setTime(new Date());
        try{
            articleService.updateArticle(article);
            ajaxReturn(true,"更新成功",response);
        }catch (Exception e){
            ajaxReturn(false,"更新失败",response);
        }
    }

}
