package com.zf.controller;

import com.alibaba.fastjson.JSON;
import com.zf.pojo.Plant;
import com.zf.service.IPlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@Scope("prototype")
@RequestMapping("plant")
public class PlantController extends BaseC{


    @Autowired
    private IPlantService plantService;

    public void setPlantService(IPlantService plantService) {
        this.plantService = plantService;
    }

    @RequestMapping("getAll")
    @Scope("prototype")
    @ResponseBody
    public void get(HttpServletResponse response){
        List<Plant> all = plantService.getAll();
        String str = JSON.toJSONString(all);
        ajaxReturn(true,str,response);
    }

    @RequestMapping("get")
    @Scope("prototype")
    @ResponseBody
    public void getMessage(HttpServletResponse response,Integer id){
        Plant plant = plantService.get(id);
        String str = JSON.toJSONString(plant);
        ajaxReturn(true,str,response);
    }

    @RequestMapping("insert")
    @Scope("prototype")
    @ResponseBody
    public void insertArticle(HttpServletResponse response,String name,String longitude,String latitude){
        Plant plant = new Plant();
        plant.setName(name);
        plant.setLatitude(latitude);
        plant.setLongitude(longitude);
        try{
            plantService.insert(plant);
            ajaxReturn(true,"添加成功",response);
        }catch (Exception e){
            ajaxReturn(false,"添加失败",response);
        }
    }

    @RequestMapping("delete")
    @Scope("prototype")
    @ResponseBody
    public void deleteArticle(HttpServletResponse response,Integer id){
        try{
            plantService.delete(id);
            ajaxReturn(true,"删除成功",response);
        }catch (Exception e){
            ajaxReturn(false,"删除失败",response);
        }
    }

    @RequestMapping("update")
    @Scope("prototype")
    @ResponseBody
    public void updateArticle(HttpServletResponse response, Integer id, String name,String longitude,String latitude){
        Plant plant = plantService.get(id);

        if (name != null)
            plant.setName(name);
        if (longitude != null)
            plant.setLongitude(longitude);
        if (latitude != null)
            plant.setLatitude(latitude);
        try{
            plantService.update(plant);
            ajaxReturn(true,"更新成功",response);
        }catch (Exception e){
            ajaxReturn(false,"更新失败",response);
        }
    }
    
}
