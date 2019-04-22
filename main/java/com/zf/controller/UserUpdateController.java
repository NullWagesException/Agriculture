package com.zf.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zf.pojo.Curing;
import com.zf.pojo.UpdateUser;
import com.zf.service.ICuringService;
import com.zf.service.IUserUpdateService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("userupdate")
public class UserUpdateController {

    @Autowired
    private IUserUpdateService updateService;

    public void setUpdateService(IUserUpdateService updateService) {
        this.updateService = updateService;
    }

    @Setter
    @Autowired
    //养护栏服务层
    private ICuringService curingService;

    @RequestMapping(value = "get")
    @ResponseBody
    public Object get(Integer id){
        UpdateUser updateUser = updateService.get(id);
        return updateUser;
    }

    @RequestMapping("getAllow")
    @ResponseBody
    public Object getAllow(Integer pageNum,Integer allow) {

        Map<String, Object> data = new HashMap<>();
        Page<Object> objects = PageHelper.startPage(pageNum, 10);
        List<UpdateUser> all = updateService.getAllow(allow);
        data.put("total", objects.getTotal());
        data.put("nowPage", pageNum);
        data.put("data", all);
        return data;

    }

    @RequestMapping("getAll")
    @ResponseBody
    public Object getAll(Integer pageNum) {

        Map<String, Object> data = new HashMap<>();
        Page<Object> objects = PageHelper.startPage(pageNum, 10);
        List<UpdateUser> all = updateService.getAll();
        data.put("total", objects.getTotal());
        data.put("nowPage", pageNum);
        data.put("data", all);
        return data;

    }

    @RequestMapping("update")
    
    @ResponseBody
    public Object update(Integer id){
        try {
            UpdateUser updateUser = updateService.get(id);
            updateUser.setAllow(1);
            Integer updateid = updateUser.getUpdateid();
            Curing curing = curingService.get(updateid);
            if (updateUser.getName() != null)
                curing.setName(updateUser.getName());
            if (updateUser.getActual() != null)
                curing.setActual(updateUser.getActual());
            if (updateUser.getActual() != null)
                curing.setActual(updateUser.getActual());
            curing.setDate(new Date());
            if (updateUser.getExpected() != null)
                curing.setExpected(updateUser.getExpected());
            if (updateUser.getFertilizer_id() != null)
                curing.setFertilizer_id(updateUser.getFertilizer_id());
            if (updateUser.getFertilizer_num() != null)
                curing.setFertilizer_num(updateUser.getFertilizer_num());
            if (updateUser.getImagepath() != null)
                curing.setImagepath(updateUser.getImagepath());
            if (updateUser.getPesticides_id() != null)
                curing.setPesticides_id(updateUser.getPesticides_id());
            if (updateUser.getPesticides_num() != null)
                curing.setPesticides_num(updateUser.getPesticides_num());
            if (updateUser.getRemarks() != null)
                curing.setRemarks(updateUser.getRemarks());
            if (updateUser.getSchedule() != null)
                curing.setSchedule(updateUser.getSchedule());
            if (updateUser.getSeedling_id() != null)
                curing.setSeedling_id(updateUser.getSeedling_id());
            if (updateUser.getSeedling_num() != null)
                curing.setSeedling_num(updateUser.getSeedling_num());
            if (updateUser.getStatus() != null)
                curing.setStatus(updateUser.getStatus());
            System.out.println();
            System.out.println(curing);
            System.out.println();
            curingService.update(curing);
            updateService.update(updateUser);
            return "true";
        }catch (Exception e){
            e.printStackTrace();
            return "false";
        }
    }

}
