package com.zf.controller;

import com.alibaba.fastjson.JSON;
import com.zf.pojo.Plant;
import com.zf.service.IPlantService;
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
@RequestMapping("plant")
public class PlantController extends BaseC{


    @Setter
    @Autowired
    private IPlantService plantService;

    @RequestMapping("getAll")
    @Scope("prototype")
    @ResponseBody
    public void get(HttpServletResponse response){
        List<Plant> all = plantService.getAll();
        Map<String,Map> allmap = new HashMap<>();
        int index = 0;
        for (Plant plant : all) {
            Map<String,String> map = new HashMap<>();
            map.put("id",plant.getId().toString());
            map.put("name",plant.getName());
            map.put("longitude",plant.getLongitude());
            map.put("latitude",plant.getLatitude());
            allmap.put(index + "",map);
            index++;
        }
        write(allmap,response);
    }

    @RequestMapping("get")
    @Scope("prototype")
    @ResponseBody
    public void getMessage(HttpServletResponse response,Integer id){
        Plant plant = plantService.get(id);
        String str = JSON.toJSONString(plant);
        Map<String,String> map = new HashMap<>();
        map.put("get",str);
        write(map,response);
    }

    @RequestMapping("insert")
    @Scope("prototype")
    @ResponseBody
    public void insertArticle(HttpServletResponse response,String name,String longitude,String latitude){
        Plant plant = new Plant();
        plant.setName(name);
        Map<String,String> map = new HashMap<>();
        plant.setLatitude(latitude);
        plant.setLongitude(longitude);
        try{
            plantService.insert(plant);
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
            plantService.delete(id);
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
    public void updateArticle(HttpServletResponse response, Integer id, String name,String longitude,String latitude){
        Plant plant = plantService.get(id);
        Map<String,String> map = new HashMap<>();

        if (name != null)
            plant.setName(name);
        if (name != null)
            plant.setLongitude(longitude);
        if (name != null)
            plant.setLatitude(latitude);
        try{
            plantService.update(plant);
            map.put("result","ture");
            write(map,response);
        }catch (Exception e){
            map.put("result","false");
            write(map,response);
        }
    }
    
}
