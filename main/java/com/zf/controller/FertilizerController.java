package com.zf.controller;

import com.alibaba.fastjson.JSON;
import com.zf.pojo.Fertilizer;
import com.zf.service.IFertilizerService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("fertilizer")
public class FertilizerController extends BaseC{


    @Setter
    @Autowired
    private IFertilizerService fertilizerService;

    @RequestMapping("getAll")
    @Scope("prototype")
    @ResponseBody
    public void get(HttpServletResponse response){
        List<Fertilizer> all = fertilizerService.getAll();
        String str = JSON.toJSONString(all);
        Map<String,String> map = new HashMap<>();
        map.put("getAll",str);
        write(map,response);
    }

    @RequestMapping("get")
    @Scope("prototype")
    @ResponseBody
    public void getMessage(HttpServletResponse response,Integer id){
        Fertilizer Fertilizer = fertilizerService.get(id);
        String str = JSON.toJSONString(Fertilizer);
        Map<String,String> map = new HashMap<>();
        map.put("get",str);
        write(map,response);
    }

    @RequestMapping("insert")
    @Scope("prototype")
    @ResponseBody
    public void insertArticle(HttpServletResponse response,String name){
        Map<String,String> map = new HashMap<>();
        Fertilizer Fertilizer = new Fertilizer();
        Fertilizer.setName(name);
        try{
            fertilizerService.insert(Fertilizer);
            map.put("result","ture");
            write(map,response);
        }catch (Exception e){
            map.put("result","false");
            write(map,response);
        }
    }

    @RequestMapping("delete")
    @Scope("prototype")
    @ResponseBody
    public void deleteArticle(HttpServletResponse response,Integer id){
        Map<String,String> map = new HashMap<>();
        try{
            fertilizerService.delete(id);
            map.put("result","ture");
            write(map,response);
        }catch (Exception e){
            map.put("result","false");
            write(map,response);
        }
    }

    @RequestMapping("update")
    @Scope("prototype")
    @ResponseBody
    public void updateArticle(HttpServletResponse response, Integer id, String name){
        Map<String,String> map = new HashMap<>();
        Fertilizer Fertilizer = fertilizerService.get(id);
        if (name != null)
            Fertilizer.setName(name);
        try{
            fertilizerService.update(Fertilizer);
            map.put("result","ture");
            write(map,response);
        }catch (Exception e){
            map.put("result","false");
            write(map,response);
        }
    }
    
}
