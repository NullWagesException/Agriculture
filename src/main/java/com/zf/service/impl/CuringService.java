package com.zf.service.impl;

import com.zf.mapper.CuringMapper;
import com.zf.pojo.Curing;
import com.zf.service.ICuringService;

import java.util.List;

public class CuringService implements ICuringService {

    private CuringMapper curingMapper;

    public void setCuringMapper(CuringMapper curingMapper) {
        this.curingMapper = curingMapper;
    }

    @Override
    public List<Curing> getAll() {
        return curingMapper.getAll();
    }

    @Override
    public Curing get(Integer id) {
        return curingMapper.get(id);
    }

    @Override
    public void insert(Curing article) {
        curingMapper.insert(article);
    }

    @Override
    public void delete(Integer id) {
        curingMapper.delete(id);
    }

    @Override
    public void update(Curing article) {
        curingMapper.update(article);
    }
}
