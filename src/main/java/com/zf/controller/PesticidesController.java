package com.zf.controller;

import com.alibaba.fastjson.JSON;
import com.zf.pojo.Pesticides;
import com.zf.service.IPesticidesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@Scope("prototype")
@RequestMapping("pesticides")
public class PesticidesController extends BaseC{


    @Autowired
    private IPesticidesService pesticidesService;

    public void setPesticidesService(IPesticidesService pesticidesService) {
        this.pesticidesService = pesticidesService;
    }

    @RequestMapping("getAll")
    @Scope("prototype")
    @ResponseBody
    public void get(HttpServletResponse response){
        List<Pesticides> all = pesticidesService.getAll();
        String str = JSON.toJSONString(all);
        ajaxReturn(true,str,response);
    }

    @RequestMapping("get")
    @Scope("prototype")
    @ResponseBody
    public void getMessage(HttpServletResponse response,Integer id){
        Pesticides Pesticides = pesticidesService.get(id);
        String str = JSON.toJSONString(Pesticides);
        ajaxReturn(true,str,response);
    }

    @RequestMapping("insert")
    @Scope("prototype")
    @ResponseBody
    public void insertArticle(HttpServletResponse response,String name){
        Pesticides Pesticides = new Pesticides();
        Pesticides.setName(name);
        try{
            pesticidesService.insert(Pesticides);
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
            pesticidesService.delete(id);
            ajaxReturn(true,"删除成功",response);
        }catch (Exception e){
            ajaxReturn(false,"删除失败",response);
        }
    }

    @RequestMapping("update")
    @Scope("prototype")
    @ResponseBody
    public void updateArticle(HttpServletResponse response, Integer id, String name){
        Pesticides Pesticides = pesticidesService.get(id);
        if (name != null)
            Pesticides.setName(name);
        try{
            pesticidesService.update(Pesticides);
            ajaxReturn(true,"更新成功",response);
        }catch (Exception e){
            ajaxReturn(false,"更新失败",response);
        }
    }
    
}
