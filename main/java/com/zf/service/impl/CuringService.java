package com.zf.service.impl;

import com.zf.mapper.CuringMapper;
import com.zf.pojo.Curing;
import com.zf.service.ICuringService;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class CuringService implements ICuringService {

    @Setter
    private CuringMapper curingMapper;

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

    @Override
    public List<Curing> getStatus(Integer status) {
        return curingMapper.getStatus(status);
    }
}
