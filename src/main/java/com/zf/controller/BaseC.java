package com.zf.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
<<<<<<< HEAD
import org.apache.commons.lang3.StringEscapeUtils;
=======
>>>>>>> 80f598f33b03bb36ae0c952d5930b8bda4376e2f

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BaseC {

    public void ajaxReturn(boolean success, String message, HttpServletResponse response){
        Map<String,Object> map = new HashMap<>();
        map.put("success",success);
        map.put("message",message);
        write(map,response);
    }

    /**
     * 将列表数据封装成JSON对象并写入响应
     * @param list
     */
    public void write(Object list,HttpServletResponse response){
        System.out.println("father write:");
        //将列表数据封装成JSON对象
        String jsonString = JSON.toJSONString(list, SerializerFeature.DisableCircularReferenceDetect);
<<<<<<< HEAD
        String str = StringEscapeUtils.unescapeEcmaScript(jsonString);
        //设置编码格式
        System.out.println(str);
=======

        //设置编码格式
        System.out.println(jsonString);
>>>>>>> 80f598f33b03bb36ae0c952d5930b8bda4376e2f
        response.setContentType("text/html;charset=utf-8");

        //获取response并将数据写入
        try {
<<<<<<< HEAD
            response.getWriter().write(str);
=======
            response.getWriter().write(jsonString);
>>>>>>> 80f598f33b03bb36ae0c952d5930b8bda4376e2f
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
