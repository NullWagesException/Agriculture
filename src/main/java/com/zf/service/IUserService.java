package com.zf.service;

import com.zf.pojo.User;

import java.util.List;

public interface IUserService {

    User findUser(User user);

    User get(Integer id);

    void updateOpenid(User user);

    List<User> getAll();

    void insert(User user);

    void update(User user);

    void delete(Integer id);
}
