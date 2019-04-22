package com.zf.service;

import com.zf.pojo.UpdateUser;

import java.util.List;

public interface IUserUpdateService {

    UpdateUser get(Integer id);

    List<UpdateUser> getAll();

    void insert(UpdateUser update);

    void update(UpdateUser update);

    void delete(Integer id);

    List<UpdateUser> getAllow(Integer allow);
}
