package com.zf.mapper;

import com.zf.pojo.User;

public interface UserMapper {

    //根据用户ID获取用户信息
    User findUser(User user);

    void insertOpenid(String openid);

    void updateOpenid(User user);
}
