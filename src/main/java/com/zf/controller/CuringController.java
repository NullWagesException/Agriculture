package com.zf.controller;

import com.alibaba.fastjson.JSON;
import com.zf.pojo.Curing;
import com.zf.service.ICuringService;
import lombok.Setter;
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


    @Setter
    @Autowired
    private ICuringService curingService;

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
    public void insertArticle(HttpServletResponse response, String imagepath, String status,
                              String expected, String actual, String fertilizer_num,
                              String pesticides_num, String seedling_num, String schedule,
                              String remarks){
        Curing curing = new Curing();
        curing.setImagepath(imagepath);
        curing.setStatus(status);
        curing.setExpected(expected);
        curing.setActual(actual);
        curing.setFertilizer_num(fertilizer_num);
        curing.setPesticides_num(pesticides_num);
        curing.setSeedling_num(seedling_num);
        curing.setSchedule(schedule);
        curing.setDate(new Date());
        curing.setRemarks(remarks);
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
    public void updateArticle(HttpServletResponse response, Integer id, String imagepath, String status,
                              String expected, String actual, String fertilizer_num,
                              String pesticides_num, String seedling_num, String schedule,
                              String remarks){
        Curing curing = curingService.get(id);
        if (imagepath != null)
            curing.setImagepath(imagepath);
        if (status != null)
            curing.setStatus(status);
        if (expected != null)
            curing.setExpected(expected);
        if (actual != null)
            curing.setActual(actual);
        if (fertilizer_num != null)
            curing.setFertilizer_num(fertilizer_num);
        if (pesticides_num != null)
            curing.setPesticides_num(pesticides_num);
        if (seedling_num != null)
            curing.setSeedling_num(seedling_num);
        if (schedule != null)
            curing.setSchedule(schedule);
        if (remarks != null)
            curing.setRemarks(remarks);
        curing.setDate(new Date());
        try{
            curingService.update(curing);
            ajaxReturn(true,"更新成功",response);
        }catch (Exception e){
            ajaxReturn(false,"更新失败",response);
        }
    }
    
}
