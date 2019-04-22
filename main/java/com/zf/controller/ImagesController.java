package com.zf.controller;

import com.zf.pojo.Images;
import com.zf.service.IImagesService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("images")
public class ImagesController extends BaseC{

    @Setter
    @Autowired
    private IImagesService imagesService;

    @RequestMapping("getAllImages")
    
    @ResponseBody
    public Object getAllImages(HttpServletResponse response){
        List<Images> allImage = imagesService.getAllImage();
        return allImage;
    }



}
