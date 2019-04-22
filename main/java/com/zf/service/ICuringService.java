package com.zf.service;

import com.zf.pojo.Curing;

import java.util.List;

public interface ICuringService {

    List<Curing> getAll();

    Curing get(Integer id);

    void insert(Curing article);

    void delete(Integer id);

    void update(Curing article);

    List<Curing> getStatus(Integer status);

}
