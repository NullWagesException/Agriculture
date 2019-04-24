package com.zf.controller;

import com.alibaba.fastjson.JSON;
import com.zf.myutils.HttpUtils;
import com.zf.pojo.User;
import com.zf.service.IUserService;
import com.zf.service.IUserUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RequestMapping("user")
@SessionAttributes("qurUser")
@Controller
public class UserController extends BaseC{

    public static Integer type = 0;

    @Autowired
    private IUserService userService;

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @Autowired
    private IUserUpdateService updateService;

    public void setUpdateService(IUserUpdateService updateService) {
        this.updateService = updateService;
    }

    private String code;
    private User myuser = new User();

    @RequestMapping("findUserById")
    @ResponseBody
    //对用户未进行登陆时，验证用户名密码以及返回登录态
    public ModelAndView findUserById(String code, String username, String password){
//        User user = userService.findUserById(id);
        ModelAndView mv = new ModelAndView("forward:/user/getKeyAndID");

        //接收前端传来的值
        this.code = code;
        myuser.setUsername(username);
        myuser.setPassword(password);
        return mv;
    }

    @RequestMapping("getKeyAndID")
    
    @ResponseBody
    public Object getKeyAndID(HttpServletResponse response, HttpSession session){
        String targetData = HttpUtils.getTargetData("https://api.weixin.qq.com/sns/jscode2session?appid=wx1a4fb0ccc09a8183&secret=e9b15594b074af72d5e03337b77fddd9&js_code=" + code + "&grant_type=authorization_code");
        String[] split = targetData.split("openid");
        String openid = split[split.length-1].substring(3,split[split.length-1].length()-2);
        System.out.println(openid);
        User byOpenId = userService.findByOpenId(openid);
        //检查用户名和密码是否正确
        User qurUser = userService.findUser(this.myuser);
        if (qurUser != null){
            //如果是另一个账户,去除前一位openID，更新为当前位
            if (byOpenId != null){
                    byOpenId.setOpenid("");
                    userService.update(byOpenId);
            }

            //存入openID
            session.setAttribute("qurUser",qurUser);
            qurUser.setOpenid(openid);
            userService.updateOpenid(qurUser);
            type = 0;
            return qurUser;
        }else{
            return "NotExistent";
        }
    }

    @RequestMapping("checkUser")
    @ResponseBody
    public boolean test(HttpSession session){
        User user = (User) session.getAttribute("qurUser");
        if (user!=null)
            return true;

        return false;
    }


    @RequestMapping("getAll")
    
    @ResponseBody
    public Object getAll(){
        List<User> all = userService.getAll();
        return all;
    }

    @RequestMapping("adminlogin")
    @ResponseBody
    public Object adminlogin(User user,HttpSession session,HttpServletResponse response, HttpServletRequest request){

        User qurUser = userService.findUser(user);
        if (qurUser == null){
            type = 0;
//            try {
//                addCookie("0",response,request);
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
            return "false";
        }
        if (qurUser.getType() == 2){
            type = qurUser.getType();
            session.setAttribute("qurUser",qurUser);
//            try {
//                addCookie(qurUser.getType()+"",response,request);
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
            return "true";
        }else{
            type = 0;
            return "false";
        }

    }

    public static void addCookie(String type,HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException {
        //创建cookie
        Cookie nameCookie = new Cookie("type", type);
        nameCookie.setPath(request.getContextPath()+"/");//设置cookie路径
        //设置cookie保存的时间 单位：秒
        nameCookie.setMaxAge(7*24*60*60);
        //将cookie添加到响应
        response.addCookie(nameCookie);
    }

    @RequestMapping("get")
    @ResponseBody
    public Object get(Integer id){
        User user = userService.get(id);
        return JSON.toJSONString(user);
    }

    @RequestMapping(value = "insert",produces="text/html; charset=UTF-8")
    @ResponseBody
    public Object insert(User insertUser){
        User user = userService.findUsername(insertUser.getUsername());
        if (user != null)
            return "用户已存在";
        try {
            userService.insert(insertUser);
            return "true";
        }catch (Exception e){
            e.printStackTrace();
            return "false";
        }
    }

    @RequestMapping("update")
    @ResponseBody
    public Object update(User updateUser){
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
            if (updateUser.getName() != null)
                user.setName(updateUser.getName());
            userService.update(user);
            return "true";
        }catch (Exception e){
            e.printStackTrace();
            return "false";
        }
    }


    @RequestMapping("delete")
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
