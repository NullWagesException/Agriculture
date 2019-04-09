package com.zf.controller;

import com.alibaba.fastjson.JSON;
import com.zf.pojo.Fertilizer;
import com.zf.service.IFertilizerService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("fertilizer")
public class FertilizerController extends BaseC{


    @Setter
    @Autowired
    private IFertilizerService fertilizerService;

    @RequestMapping("getAll")
    @ResponseBody
    public void get(HttpServletResponse response){
        List<Fertilizer> all = fertilizerService.getAll();
        String str = JSON.toJSONString(all);
        ajaxReturn(true,str,response);
    }

    @RequestMapping("get")
    @ResponseBody
    public void getMessage(HttpServletResponse response,Integer id){
        Fertilizer Fertilizer = fertilizerService.get(id);
        String str = JSON.toJSONString(Fertilizer);
        ajaxReturn(true,str,response);
    }

    @RequestMapping("insert")
    @ResponseBody
    public void insertArticle(HttpServletResponse response,String name){
        Fertilizer Fertilizer = new Fertilizer();
        Fertilizer.setName(name);
        try{
            fertilizerService.insert(Fertilizer);
            ajaxReturn(true,"添加成功",response);
        }catch (Exception e){
            ajaxReturn(false,"添加失败",response);
        }
    }

    @RequestMapping("delete")
    @ResponseBody
    public void deleteArticle(HttpServletResponse response,Integer id){
        try{
            fertilizerService.delete(id);
            ajaxReturn(true,"删除成功",response);
        }catch (Exception e){
            ajaxReturn(false,"删除失败",response);
        }
    }

    @RequestMapping("update")
    @ResponseBody
    public void updateArticle(HttpServletResponse response, Integer id, String name){
        Fertilizer Fertilizer = fertilizerService.get(id);
        if (name != null)
            Fertilizer.setName(name);
        try{
            fertilizerService.update(Fertilizer);
            ajaxReturn(true,"更新成功",response);
        }catch (Exception e){
            ajaxReturn(false,"更新失败",response);
        }
    }
    
}
