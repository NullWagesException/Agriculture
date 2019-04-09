package com.zf.myutils;

import okhttp3.*;

import java.io.IOException;

public class HttpUtils {
    public static final MediaType JSON= MediaType.parse("application/json; charset=utf-8");

    private static OkHttpClient client=new OkHttpClient();

    //发送post请求
    public static String postTargetData(String  url ,String content )
    {
        String result=null;
        RequestBody body=RequestBody.create(JSON,content);
        Request request=new Request.Builder().url(url).post(body).build();
        Response res=null;
        try {
            res = client.newCall(request).execute();
            result=res.body().string();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    //发送get请求
    public static String getTargetData(String url)
    {
        String result=null;
        Request request=new Request.Builder().url(url).build();
        Response res=null;
        try {
            res=client.newCall(request).execute();
            result=res.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}