package com.zf.service.impl;

import com.zf.mapper.SeedingMapper;
import com.zf.pojo.Seeding;
import com.zf.service.ISeedingService;

import java.util.List;

public class SeedingService implements ISeedingService {

    private SeedingMapper seedingMapper;

    public void setSeedingMapper(SeedingMapper seedingMapper) {
        this.seedingMapper = seedingMapper;
    }

    @Override
    public List<Seeding> getAll() {
        return seedingMapper.getAll();
    }

    @Override
    public Seeding get(Integer id) {
        return seedingMapper.get(id);
    }

    @Override
    public void insert(Seeding article) {
        seedingMapper.insert(article);
    }

    @Override
    public void delete(Integer id) {
        seedingMapper.delete(id);
    }

    @Override
    public void update(Seeding article) {
        seedingMapper.update(article);
    }
}
