package com.zf.service.impl;

import com.zf.mapper.FertilizerMapper;
import com.zf.pojo.Fertilizer;
import com.zf.service.IFertilizerService;

import java.util.List;

public class FertilizerService implements IFertilizerService {
    
    private FertilizerMapper fertilizerMapper;

    public void setFertilizerMapper(FertilizerMapper fertilizerMapper) {
        this.fertilizerMapper = fertilizerMapper;
    }

    @Override
    public List<Fertilizer> getAll() {
        return fertilizerMapper.getAll();
    }

    @Override
    public Fertilizer get(Integer id) {
        return fertilizerMapper.get(id);
    }

    @Override
    public void insert(Fertilizer article) {
        fertilizerMapper.insert(article);
    }

    @Override
    public void delete(Integer id) {
        fertilizerMapper.delete(id);
    }

    @Override
    public void update(Fertilizer article) {
        fertilizerMapper.update(article);
    }
}
