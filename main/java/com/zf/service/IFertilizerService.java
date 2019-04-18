package com.zf.service;

import com.zf.pojo.Fertilizer;

import java.util.List;

public interface IFertilizerService {

    List<Fertilizer> getAll();

    Fertilizer get(Integer id);

    void insert(Fertilizer article);

    void delete(Integer id);

    void update(Fertilizer article);
    
}
