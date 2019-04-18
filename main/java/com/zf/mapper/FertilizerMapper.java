package com.zf.mapper;

import com.zf.pojo.Fertilizer;

import java.util.List;

public interface FertilizerMapper {

    List<Fertilizer> getAll();

    Fertilizer get(Integer id);

    void insert(Fertilizer article);

    void delete(Integer id);

    void update(Fertilizer article);

}
