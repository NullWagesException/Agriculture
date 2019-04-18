package com.zf.mapper;

import com.zf.pojo.Seedling;

import java.util.List;

public interface SeedlingMapper {

    List<Seedling> getAll();

    Seedling get(Integer id);

    void insert(Seedling article);

    void delete(Integer id);

    void update(Seedling article);

}
