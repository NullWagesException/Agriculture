package com.zf.service;

import com.zf.pojo.Pesticides;

import java.util.List;

public interface IPesticidesService {

    List<Pesticides> getAll();

    Pesticides get(Integer id);

    void insert(Pesticides article);

    void delete(Integer id);

    void update(Pesticides article);
    
}
