package com.zf.service.impl;

import com.zf.mapper.ImagesMapper;
import com.zf.pojo.Images;
import com.zf.service.IImagesService;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class ImagesService implements IImagesService {

    @Setter
    private ImagesMapper imagesMapper;

    @Override
    public List<Images> getAllImage() {
        return imagesMapper.getAllImage();
    }
}
