package com.zf.service;

import com.zf.pojo.Seedling;

import java.util.List;

public interface ISeedlingService {

    List<Seedling> getAll();

    Seedling get(Integer id);

    void insert(Seedling article);

    void delete(Integer id);

    void update(Seedling article);

}
