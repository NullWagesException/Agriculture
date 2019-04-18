package com.zf.mapper;

import com.zf.pojo.User;

import java.util.List;

public interface UserMapper {

    //根据用户名和密码获取用户信息
    User findUser(User user);

    //根据用户ID获取用户信息
    User get(Integer id);

    void updateOpenid(User user);

    List<User> getAll();

    void insert(User user);

    void update(User user);

    void delete(Integer id);
}
