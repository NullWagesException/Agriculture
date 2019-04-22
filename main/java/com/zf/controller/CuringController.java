package com.zf.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zf.pojo.*;
import com.zf.service.*;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("curing")
public class CuringController extends BaseC{

    @Autowired
    private IUserService userService;

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }


    @Setter
    @Autowired
    //养护栏服务层
    private ICuringService curingService;

    @Autowired
    private IUserUpdateService updateService;

    public void setUpdateService(IUserUpdateService updateService) {
        this.updateService = updateService;
    }


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
    
    @ResponseBody
    public Object get(HttpServletResponse response,Integer pageNum){

        Map<String, Object> data = new HashMap<>();
        Page<Object> objects = PageHelper.startPage(pageNum, 10);
        List<Curing> all = curingService.getAll();
        data.put("total", objects.getTotal());
        data.put("nowPage", pageNum);
        data.put("data", all);
        return data;

    }

    @RequestMapping("getScale")
    @ResponseBody
    public Object getScale(){
        List<Curing> all = curingService.getAll();
        int status_0 = 0;
        int status_1 = 0;
        int status_2 = 0;

        for (Curing curing : all) {
            if (curing.getStatus().equals("0"))
                status_0++;
            if (curing.getStatus().equals("1"))
                status_1++;
            if (curing.getStatus().equals("2"))
                status_2++;
        }
        Map map = new HashMap();
        map.put("0",status_0);
        map.put("1",status_1);
        map.put("2",status_2);
        return map;
    }

    @RequestMapping("getTime")
    @ResponseBody
    public Object getTime(){
        List<Curing> all = curingService.getAll();
        Map map = new LinkedHashMap();
        List<Long> timelist = new ArrayList<>();
        for (Curing curing : all) {
            timelist.add(curing.getDate().getTime());
        }
        Collections.sort(timelist);
        for (Long aLong : timelist) {
            Date date = new Date(aLong);
            date.getTime();
            String time = date.toLocaleString();
            time = time.split(" ")[0];
            if (map.get(time) == null)
                map.put(time,1);
            else{
                int count = (int) map.get(time);
                map.put(time,++count);
            }
        }
        return map;
    }



    //根据状态查询
    @RequestMapping("getStatus")
    @ResponseBody
    public Object getStatus(Integer pageNum,Integer status){

        Map<String, Object> data = new HashMap<>();
        Page<Object> objects = PageHelper.startPage(pageNum, 10);
        List<Curing> all = curingService.getStatus(status);
        data.put("total", objects.getTotal());
        data.put("nowPage", pageNum);
        data.put("data", all);
        return data;

    }



    @RequestMapping("get")
    
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


    @RequestMapping("test")
    @ResponseBody
    public Object test(){
        Map<String, Object> data = new HashMap<>();
        Integer pageNum = 1;
        Integer pageSize = 5;
        Page<Object> objects = PageHelper.startPage(pageNum, pageSize);
        List<Curing> all = curingService.getAll();
        data.put("total", objects.getTotal());
        data.put("nowPage", pageNum);
        data.put("data", all);
        return data;
    }

    @RequestMapping("insert")
    
    @ResponseBody
    public void insertArticle(HttpServletResponse response,Curing curing){
        Map<String,String> map = new HashMap<>();
        System.out.println(curing);
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
    @ResponseBody
    public Object updateArticle(Curing curing, HttpSession session,String openid){
        Map<String,String> map = new HashMap<>();
        System.out.println();
        System.out.println(curing);
        System.out.println();
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
        curing_r.setDate(new Date());


        UpdateUser updateUser = new UpdateUser();
        updateUser.setName(curing_r.getName());
        updateUser.setUpdateid(curing_r.getId());
        updateUser.setActual(curing_r.getActual());
        updateUser.setExpected(curing_r.getExpected());
        updateUser.setFertilizer_id(curing_r.getFertilizer_id());
        updateUser.setFertilizer_num(curing_r.getFertilizer_num());
        updateUser.setImagepath(curing_r.getImagepath());
        updateUser.setPesticides_id(curing_r.getPesticides_id());
        updateUser.setPesticides_num(curing_r.getPesticides_num());
        updateUser.setRemarks(curing_r.getRemarks());
        updateUser.setSchedule(curing_r.getSchedule());
        updateUser.setSeedling_id(curing_r.getSeedling_id());
        updateUser.setSeedling_num(curing_r.getSeedling_num());
        updateUser.setStatus(curing_r.getStatus());
        //从session中获取用户数据
        User qurUser = userService.findByOpenId(openid);

        if (qurUser == null){
            if(UserController.type == 2) {
                curingService.update(curing_r);
                map.put("result","ture");
                return map;
            }
        }else if(qurUser.getType() == 2) {
            curingService.update(curing_r);
            map.put("result","ture");
            return map;
        }
        if (qurUser!=null)
            updateUser.setUsername(qurUser.getName());
        updateUser.setAllow(0);
        System.out.println();
        System.out.println(updateUser);
        System.out.println();
        try{
            updateService.insert(updateUser);
            map.put("result","ture");
        }catch (Exception e){
            map.put("result","false");
        }
        return map;
    }
    
}
