package com.zf.service;

import com.zf.pojo.User;

public interface IUserService {

    User findUser(User user);

    void insertOpenid(String openid);

    void updateOpenid(User user);
}
