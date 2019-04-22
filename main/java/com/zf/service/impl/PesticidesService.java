package com.zf.service.impl;

import com.zf.mapper.PesticidesMapper;
import com.zf.pojo.Pesticides;
import com.zf.service.IPesticidesService;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
public class PesticidesService implements IPesticidesService {
    
    @Setter
    private PesticidesMapper pesticidesMapper;
    
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
