package com.zf.service;

import com.zf.pojo.Plant;

import java.util.List;

public interface IPlantService {

    List<Plant> getAll();

    Plant get(Integer id);

    void insert(Plant article);

    void delete(Integer id);

    void update(Plant article);

}
