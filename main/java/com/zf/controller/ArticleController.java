package com.zf.controller;

import com.zf.pojo.Article;
import com.zf.service.IArticleService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("article")
public class ArticleController extends BaseC {

    @Setter
    @Autowired
    private IArticleService articleService;

    //获取简略信息
    @RequestMapping("getAll")
    @Scope("prototype")
    @ResponseBody
    public void getSimpleMessage(HttpServletResponse response){
        List<Article> simpleMessage = articleService.getSimpleMessage();
        Map<String,Map> all = new HashMap<>();
        int index = 0;
        for (Article article : simpleMessage) {
            Map<String,String> map = new HashMap<>();
            map.put("id",article.getId().toString());
            map.put("imgUrl",article.getImagepath());
            map.put("text",article.getTitle());
            map.put("date",article.getTime().toLocaleString());
            all.put(index + "",map);
            index++;
        }
        write(all,response);
    }

    //根据ID获取某个信息
    @RequestMapping("get")
    @Scope("prototype")
    @ResponseBody
    public void getMessage(HttpServletResponse response,Integer id){
        Article message = articleService.getMessage(id);
        if (message == null)
            return;
        Map<String,String> map = new HashMap<>();
        map.put("id",message.getId().toString());
        map.put("imgUrl",message.getImagepath());
        map.put("title",message.getTitle());
        map.put("date",message.getTime().toLocaleString());
        map.put("content",message.getText());
        write(map,response);
    }

//   添加文章
    @RequestMapping("insert")
    @Scope("prototype")
    @ResponseBody
    public void insertArticle(HttpServletResponse response,Article article){
        Map<String,String> map = new HashMap<>();
        article.setTime(new Date());
        try{
            articleService.insertArticle(article);
            map.put("result","ture");
            write(map,response);
        }catch (Exception e){
            map.put("result","false");
            write(map,response);
        }
    }

//    删除文章
    @RequestMapping("delete")
    @Scope("prototype")
    @ResponseBody
    public void deleteArticle(HttpServletResponse response,Integer id){
        Map<String,String> map = new HashMap<>();
        try{
            articleService.deleteArticle(id);
            map.put("result","ture");
            write(map,response);
        }catch (Exception e){
            map.put("result","false");
            write(map,response);
        }
    }

//    更新文章
    @RequestMapping("update")
    @Scope("prototype")
    @ResponseBody
    public void updateArticle(HttpServletResponse response,Article article){
        Map<String,String> map = new HashMap<>();
        Article article_r = articleService.getMessage(article.getId());
        if (article.getTitle() != null)
            article_r.setTitle(article.getTitle());
        if (article.getImagepath() != null)
            article_r.setImagepath(article.getImagepath());
        if (article.getText() != null)
            article_r.setText(article.getText());
        article_r.setTime(new Date());
        try{
            articleService.updateArticle(article_r);
            map.put("result","ture");
            write(map,response);
        }catch (Exception e){
            map.put("result","false");
            write(map,response);
        }
    }

}
