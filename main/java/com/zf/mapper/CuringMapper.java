package com.zf.mapper;

import com.zf.pojo.Curing;

import java.util.List;

public interface CuringMapper {

    List<Curing> getAll();

    Curing get(Integer id);

    List<Curing> getStatus(Integer status);

    void insert(Curing article);

    void delete(Integer id);

    void update(Curing article);

}
