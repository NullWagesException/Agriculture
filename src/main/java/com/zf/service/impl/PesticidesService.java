package com.zf.service.impl;

import com.zf.mapper.PesticidesMapper;
import com.zf.pojo.Pesticides;
import com.zf.service.IPesticidesService;

import java.util.List;

public class PesticidesService implements IPesticidesService {
    
    private PesticidesMapper pesticidesMapper;

    public void setPesticidesMapper(PesticidesMapper pesticidesMapper) {
        this.pesticidesMapper = pesticidesMapper;
    }

    @Override
    public List<Pesticides> getAll() {
        return pesticidesMapper.getAll();
    }

    @Override
    public Pesticides get(Integer id) {
        return pesticidesMapper.get(id);
    }

    @Override
    public void insert(Pesticides article) {
        pesticidesMapper.insert(article);
    }

    @Override
    public void delete(Integer id) {
        pesticidesMapper.delete(id);
    }

    @Override
    public void update(Pesticides article) {
        pesticidesMapper.update(article);
    }
}
