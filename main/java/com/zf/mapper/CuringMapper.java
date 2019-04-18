package com.zf.mapper;

import com.zf.pojo.Curing;

import java.util.List;

public interface CuringMapper {

    List<Curing> getAll();

    Curing get(Integer id);

    void insert(Curing article);

    void delete(Integer id);

    void update(Curing article);

}
