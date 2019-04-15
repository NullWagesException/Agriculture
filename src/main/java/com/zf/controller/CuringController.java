package com.zf.controller;

import com.alibaba.fastjson.JSON;
import com.zf.pojo.Curing;
import com.zf.service.ICuringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("curing")
public class CuringController extends BaseC{


    @Autowired
    private ICuringService curingService;

    public void setCuringService(ICuringService curingService) {
        this.curingService = curingService;
    }

    @RequestMapping("getAll")
    @ResponseBody
    public void get(HttpServletResponse response){
        List<Curing> all = curingService.getAll();
        String str = JSON.toJSONString(all);
        ajaxReturn(true,str,response);
    }

    @RequestMapping("get")
    @ResponseBody
    public void getMessage(HttpServletResponse response,Integer id){
        Curing curing = curingService.get(id);
        String str = JSON.toJSONString(curing);
        ajaxReturn(true,str,response);
    }

    @RequestMapping("insert")
    @ResponseBody
    public void insertArticle(HttpServletResponse response, Curing curing){
        curing.setDate(new Date());
        try{
            curingService.insert(curing);
            ajaxReturn(true,"添加成功",response);
        }catch (Exception e){
            ajaxReturn(false,"添加失败",response);
        }
    }

    @RequestMapping("delete")
    @ResponseBody
    public void deleteArticle(HttpServletResponse response,Integer id){
        try{
            curingService.delete(id);
            ajaxReturn(true,"删除成功",response);
        }catch (Exception e){
            ajaxReturn(false,"删除失败",response);
        }
    }

    @RequestMapping("update")
    @ResponseBody
    public void updateArticle(HttpServletResponse response,Curing curing){
        Curing curing_r = curingService.get(curing.getId());
        if (curing.getImagepath() != null)
            curing_r.setImagepath(curing.getImagepath());
        if (curing.getStatus() != null)
            curing_r.setStatus(curing.getStatus());
        if (curing.getExpected() != null)
            curing_r.setExpected(curing.getExpected());
        if (curing.getActual() != null)
            curing.setActual(curing.getActual());
        if (curing.getFertilizer_num() != null)
            curing_r.setFertilizer_num(curing.getFertilizer_num());
        if (curing.getPesticides_num() != null)
            curing_r.setPesticides_num(curing_r.getPesticides_num());
        if (curing.getSeedling_num() != null)
            curing_r.setSeedling_num(curing_r.getSeedling_num());
        if (curing.getSchedule() != null)
            curing_r.setSchedule(curing.getSchedule());
        if (curing.getRemarks() != null)
            curing_r.setRemarks(curing.getRemarks());
        curing.setDate(new Date());
        try{
            curingService.update(curing);
            ajaxReturn(true,"更新成功",response);
        }catch (Exception e){
            ajaxReturn(false,"更新失败",response);
        }
    }
    
}
