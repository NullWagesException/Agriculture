package com.zf.controller;

import com.zf.pojo.Images;
import com.zf.service.IImagesService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("images")
public class ImagesController extends BaseC{

    @Setter
    @Autowired
    private IImagesService imagesService;

    @RequestMapping("getAllImages")
    @ResponseBody
    public void getAllImages(HttpServletResponse response){

        List<Images> allImage = imagesService.getAllImage();
        List<String> path = new ArrayList<>();
        for (Images images : allImage) {
            path.add(images.getImagepath());
        }

        StringBuilder str = new StringBuilder();
        str.append("[");
        for (int i = 0;i< path.size()-2;i++){
            str.append("'");
            str.append(path.get(i));
            str.append("'");
            str.append(",");
        }

        str.append("'");
        str.append(path.get(path.size()-1));
        str.append("'");

        str.append("]");
        ajaxReturn(true,str.toString(),response);

    }

}
