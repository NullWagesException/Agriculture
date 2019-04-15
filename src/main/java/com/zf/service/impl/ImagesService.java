package com.zf.service.impl;

import com.zf.mapper.ImagesMapper;
import com.zf.pojo.Images;
import com.zf.service.IImagesService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class ImagesService implements IImagesService {

    private ImagesMapper imagesMapper;

    public void setImagesMapper(ImagesMapper imagesMapper) {
        this.imagesMapper = imagesMapper;
    }

    @Override
    public List<Images> getAllImage() {
        return imagesMapper.getAllImage();
    }
}
