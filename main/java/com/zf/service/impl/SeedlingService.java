package com.zf.service.impl;

import com.zf.mapper.SeedlingMapper;
import com.zf.pojo.Seedling;
import com.zf.service.ISeedlingService;
import lombok.Setter;

import java.util.List;

public class SeedlingService implements ISeedlingService {

    @Setter
    private SeedlingMapper seedlingMapper;

    @Override
    public List<Seedling> getAll() {
        return seedlingMapper.getAll();
    }

    @Override
    public Seedling get(Integer id) {
        return seedlingMapper.get(id);
    }

    @Override
    public void insert(Seedling article) {
        seedlingMapper.insert(article);
    }

    @Override
    public void delete(Integer id) {
        seedlingMapper.delete(id);
    }

    @Override
    public void update(Seedling article) {
        seedlingMapper.update(article);
    }
}
