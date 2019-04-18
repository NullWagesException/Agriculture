package com.zf.controller;

import com.alibaba.fastjson.JSON;
import com.zf.myutils.HttpUtils;
import com.zf.pojo.User;
import com.zf.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("user")
@Controller
public class UserController extends BaseC{

    @Autowired
    private IUserService userService;

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    private String code;
    private User user = new User();

    @RequestMapping("findUserById")
    @Scope("prototype")
    @ResponseBody
    //对用户未进行登陆时，验证用户名密码以及返回登录态
    public ModelAndView findUserById(@PathVariable("code") String code, String username, String password){
//        User user = userService.findUserById(id);
        ModelAndView mv = new ModelAndView("forward:/user/getKeyAndID");

        //接收前端传来的值
        this.code = code;
        user.setUsername(username);
        user.setPassword(password);
        return mv;
    }

    @RequestMapping("getKeyAndID")
    @Scope("prototype")
    @ResponseBody
    public void getKeyAndID(HttpServletResponse response){
        String targetData = HttpUtils.getTargetData("https://api.weixin.qq.com/sns/jscode2session?appid=wx1a4fb0ccc09a8183&secret=e9b15594b074af72d5e03337b77fddd9&js_code=" + code + "&grant_type=authorization_code");
        String[] split = targetData.split("'");
        String openid = split[split.length-1];
        System.out.println(openid);
        //检查用户名和密码是否正确
        User qurUser = userService.findUser(this.user);
        if (qurUser != null){
            //存入openID
            userService.updateOpenid(user);
            ajaxReturn(true,targetData,response);
        }
    }

    @RequestMapping("getAll")
    @Scope("prototype")
    @ResponseBody
    public Object getAll(){
        Map<String,User> map = new HashMap<>();
        List<User> all = userService.getAll();
        int index = 0;
        for (User user1 : all) {
            map.put(index + "",user1);
            index++;
        }
        return map;
    }

    @RequestMapping("get")
    @Scope("prototype")
    @ResponseBody
    public Object get(Integer id){
        User user = userService.get(id);
        return JSON.toJSONString(user);
    }

    @RequestMapping("insert")
    @Scope("prototype")
    @ResponseBody
    public Object insert(@RequestParam User insertUser){
        try {
            userService.insert(insertUser);
            return "true";
        }catch (Exception e){
            e.printStackTrace();
            return "false";
        }
    }

    @RequestMapping("update")
    @Scope("prototype")
    @ResponseBody
    public Object update(@RequestParam User updateUser){
        try {
            User user = userService.get(updateUser.getId());
            if (updateUser.getUsername() != null)
                user.setUsername(updateUser.getUsername());
            if (updateUser.getPassword() != null)
                user.setPassword(updateUser.getPassword());
            if (updateUser.getPhone() != null)
                user.setPhone(updateUser.getPhone());
            if (updateUser.getOpenid() != null)
                user.setOpenid(updateUser.getOpenid());
            if (updateUser.getPosition() != null)
                user.setPosition(updateUser.getPosition());
            userService.insert(user);
            return "true";
        }catch (Exception e){
            e.printStackTrace();
            return "false";
        }
    }


    @RequestMapping("delete")
    @Scope("prototype")
    @ResponseBody
    public Object delete(Integer id){
        try {
            userService.delete(id);
            return "true";
        }catch (Exception e){
            e.printStackTrace();
            return "false";
        }
    }




}
