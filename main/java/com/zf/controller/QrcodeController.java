package com.zf.controller;

import com.zf.myutils.CreateQRCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("qrcode")
public class QrcodeController {

    @RequestMapping("getUrl")
    @ResponseBody
    public Object getUrl(String id){
        String path = CreateQRCode.create(id);
        return "\\dataResourceImages\\qrcode\\" + path;
    }

}
