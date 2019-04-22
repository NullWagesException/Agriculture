package com.zf.service.impl;

import com.zf.mapper.FertilizerMapper;
import com.zf.pojo.Fertilizer;
import com.zf.service.IFertilizerService;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
public class FertilizerService implements IFertilizerService {
    
    @Setter
    private FertilizerMapper fertilizerMapper;
    
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
