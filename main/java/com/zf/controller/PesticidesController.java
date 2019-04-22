package com.zf.controller;

import com.alibaba.fastjson.JSON;
import com.zf.pojo.Pesticides;
import com.zf.service.IPesticidesService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("pesticides")
public class PesticidesController extends BaseC{


    @Setter
    @Autowired
    private IPesticidesService pesticidesService;

    @RequestMapping("getAll")
    
    @ResponseBody
    public Object get(HttpServletResponse response){
        List<Pesticides> all = pesticidesService.getAll();
        return all;
    }

    @RequestMapping("get")
    
    @ResponseBody
    public void getMessage(HttpServletResponse response,Integer id){
        Pesticides Pesticides = pesticidesService.get(id);
        String str = JSON.toJSONString(Pesticides);
        Map<String,String> map = new HashMap<>();
        map.put("get",str);
        write(map,response);
    }

    @RequestMapping("insert")
    
    @ResponseBody
    public void insertArticle(HttpServletResponse response,String name){
        Pesticides Pesticides = new Pesticides();
        Pesticides.setName(name);
        Map<String,String> map = new HashMap<>();
        try{
            pesticidesService.insert(Pesticides);
            map.put("result","ture");
            write(map,response);
        }catch (Exception e){
            map.put("result","false");
            write(map,response);
        }
    }

    @RequestMapping("delete")
    
    @ResponseBody
    public void deleteArticle(HttpServletResponse response,Integer id){
        Map<String,String> map = new HashMap<>();
        try{
            pesticidesService.delete(id);
            map.put("result","ture");
            write(map,response);
        }catch (Exception e){
            map.put("result","false");
            write(map,response);
        }
    }

    @RequestMapping("update")
    
    @ResponseBody
    public void updateArticle(HttpServletResponse response, Integer id, String name){
        Pesticides Pesticides = pesticidesService.get(id);
        Map<String,String> map = new HashMap<>();
        if (name != null)
            Pesticides.setName(name);
        try{
            pesticidesService.update(Pesticides);
            map.put("result","ture");
            write(map,response);
        }catch (Exception e){
            map.put("result","false");
            write(map,response);
        }
    }
    
}
