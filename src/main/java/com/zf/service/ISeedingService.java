package com.zf.service;

import com.zf.pojo.Seeding;

import java.util.List;

public interface ISeedingService {

    List<Seeding> getAll();

    Seeding get(Integer id);

    void insert(Seeding article);

    void delete(Integer id);

    void update(Seeding article);

}
