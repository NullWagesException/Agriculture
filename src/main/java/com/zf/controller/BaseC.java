package com.zf.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang3.StringEscapeUtils;


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
        String str = StringEscapeUtils.unescapeEcmaScript(jsonString);
        //设置编码格式
        System.out.println(str);
        response.setContentType("text/html;charset=utf-8");

        //获取response并将数据写入
        try {
            response.getWriter().write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
