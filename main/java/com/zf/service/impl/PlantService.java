package com.zf.service.impl;

import com.zf.mapper.PlantMapper;
import com.zf.pojo.Plant;
import com.zf.service.IPlantService;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
public class PlantService implements IPlantService {

    @Setter
    private PlantMapper plantMapper;

    @Override
    public List<Plant> getAll() {
        return plantMapper.getAll();
    }

    @Override
    public Plant get(Integer id) {
        return plantMapper.get(id);
    }

    @Override
    public void insert(Plant article) {
        plantMapper.insert(article);
    }

    @Override
    public void delete(Integer id) {
        plantMapper.delete(id);
    }

    @Override
    public void update(Plant article) {
        plantMapper.update(article);
    }
}
