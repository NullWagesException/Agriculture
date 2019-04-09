package com.zf.controller;

import com.alibaba.fastjson.JSON;
import com.zf.pojo.Seeding;
import com.zf.service.ISeedingService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("seeding")
public class SeedingController extends BaseC{


    @Setter
    @Autowired
    private ISeedingService seedingService;

    @RequestMapping("getAll")
    @ResponseBody
    public void get(HttpServletResponse response){
        List<Seeding> all = seedingService.getAll();
        String str = JSON.toJSONString(all);
        ajaxReturn(true,str,response);
    }

    @RequestMapping("get")
    @ResponseBody
    public void getMessage(HttpServletResponse response,Integer id){
        Seeding seeding = seedingService.get(id);
        String str = JSON.toJSONString(seeding);
        ajaxReturn(true,str,response);
    }

    @RequestMapping("insert")
    @ResponseBody
    public void insertArticle(HttpServletResponse response,String name){
        Seeding seeding = new Seeding();
        seeding.setName(name);
        try{
            seedingService.insert(seeding);
            ajaxReturn(true,"添加成功",response);
        }catch (Exception e){
            ajaxReturn(false,"添加失败",response);
        }
    }

    @RequestMapping("delete")
    @ResponseBody
    public void deleteArticle(HttpServletResponse response,Integer id){
        try{
            seedingService.delete(id);
            ajaxReturn(true,"删除成功",response);
        }catch (Exception e){
            ajaxReturn(false,"删除失败",response);
        }
    }

    @RequestMapping("update")
    @ResponseBody
    public void updateArticle(HttpServletResponse response, Integer id, String name){
        Seeding seeding = seedingService.get(id);
        if (name != null)
            seeding.setName(name);
        try{
            seedingService.update(seeding);
            ajaxReturn(true,"更新成功",response);
        }catch (Exception e){
            ajaxReturn(false,"更新失败",response);
        }
    }
    
}
