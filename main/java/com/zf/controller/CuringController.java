package com.zf.controller;

import com.alibaba.fastjson.JSON;
import com.zf.pojo.Curing;
import com.zf.pojo.Fertilizer;
import com.zf.pojo.Pesticides;
import com.zf.pojo.Seedling;
import com.zf.service.ICuringService;
import com.zf.service.IFertilizerService;
import com.zf.service.IPesticidesService;
import com.zf.service.ISeedlingService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping("curing")
public class CuringController extends BaseC{


    @Setter
    @Autowired
    //养护栏服务层
    private ICuringService curingService;

    //肥料
    @Setter
    @Autowired
    private IFertilizerService fertilizerService;

    //农药
    @Setter
    @Autowired
    private IPesticidesService pesticidesService;

    //树苗
    @Setter
    @Autowired
    private ISeedlingService seedingService;


    @RequestMapping("getAll")
    @Scope("prototype")
    @ResponseBody
    public Object get(HttpServletResponse response){
        List<Curing> all = curingService.getAll();
        String str = JSON.toJSONString(all);
        Map<String,String> map = new HashMap<>();
        map.put("getAll",str);
        return map;
    }

    @RequestMapping("get")
    @Scope("prototype")
    @ResponseBody
    public void getMessage(HttpServletResponse response,Integer id){
        Curing curing = curingService.get(id);
        if (curing == null)
            return;
        Map<String,String> map_curing = new HashMap<>();
        //存入养护栏所有信息
        map_curing.put("id",curing.getId().toString());
        map_curing.put("imagepath",curing.getImagepath());
        map_curing.put("status",curing.getStatus());
        map_curing.put("expected",curing.getExpected());
        map_curing.put("name",curing.getName());
        map_curing.put("actual",curing.getActual());
        map_curing.put("fertilizer_num",curing.getFertilizer_num());
        map_curing.put("pesticides_num",curing.getPesticides_num());
        map_curing.put("seedling_num",curing.getSeedling_num());
        map_curing.put("fertilizer_id",curing.getFertilizer_id().toString());
        map_curing.put("pesticides_id",curing.getPesticides_id().toString());
        map_curing.put("seedling_id",curing.getSeedling_id().toString());
        map_curing.put("schedule",curing.getSchedule());
        map_curing.put("date",curing.getDate().toLocaleString());
        map_curing.put("remarks",curing.getRemarks());

        //获取肥料信息
        List<String> list_fertilizer = new ArrayList<>();
        List<Fertilizer> fertilizer = fertilizerService.getAll();
        for (Fertilizer fertilizer1 : fertilizer) {
            list_fertilizer.add(fertilizer1.getName());
        }

        //获取农药信息
        List<String> list_pesticides = new ArrayList<>();
        List<Pesticides> pesticides = pesticidesService.getAll();
        for (Pesticides pesticides1 : pesticides) {
            list_pesticides.add(pesticides1.getName());
        }

        //获取树苗信息
        List<String> list_seeding = new ArrayList<>();
        List<Seedling> seedling = seedingService.getAll();
        for (Seedling seedling1 : seedling) {
            list_seeding.add(seedling1.getName());
        }

        Map<String,Object> map_all = new HashMap<>();
        map_all.put("curing",map_curing);
        map_all.put("fertilizer",list_fertilizer);
        map_all.put("pesticides",list_pesticides);
        map_all.put("seeding",list_seeding);
        write(map_all,response);
    }

    @RequestMapping("insert")
    @Scope("prototype")
    @ResponseBody
    public void insertArticle(HttpServletResponse response, Curing curing){
        Map<String,String> map = new HashMap<>();
        curing.setDate(new Date());
        try{
            curingService.insert(curing);
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
            curingService.delete(id);
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
    public Object updateArticle(@RequestBody Curing curing){
        Map<String,String> map = new HashMap<>();
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
            curing_r.setPesticides_num(curing.getPesticides_num());
        if (curing.getSeedling_num() != null)
            curing_r.setSeedling_num(curing.getSeedling_num());
        if (curing.getSchedule() != null)
            curing_r.setSchedule(curing.getSchedule());
        if (curing.getRemarks() != null)
            curing_r.setRemarks(curing.getRemarks());
        if (curing.getFertilizer_id() != null)
            curing_r.setFertilizer_id(curing.getFertilizer_id());
        if (curing.getName() != null)
            curing_r.setName(curing.getName());
        if (curing.getPesticides_id() != null)
            curing_r.setPesticides_id(curing.getPesticides_id());
        if (curing.getSeedling_id() != null)
            curing_r.setSeedling_id(curing.getSeedling_id());
        curing.setDate(new Date());
        try{
            curingService.update(curing_r);
            map.put("result","ture");
        }catch (Exception e){
            map.put("result","false");
        }
        return map;
    }
    
}
