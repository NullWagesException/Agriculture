package com.zf.controller;

import com.zf.pojo.Images;
import com.zf.service.IImagesService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("images")
public class ImagesController extends BaseC{

    @Setter
    @Autowired
    private IImagesService imagesService;

    @RequestMapping("getAllImages")
    @Scope("prototype")
    @ResponseBody
    public void getAllImages(HttpServletResponse response){
        Map<String,String> map = new HashMap<>();
        List<Images> allImage = imagesService.getAllImage();
        int index = 1;
        for (Images images : allImage) {
            map.put(index + "",images.getImagepath());
            index++;
        }
        write(map,response);

    }

}
