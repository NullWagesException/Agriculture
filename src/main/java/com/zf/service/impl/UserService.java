package com.zf.service.impl;

import com.zf.mapper.UserMapper;
import com.zf.pojo.User;
import com.zf.service.IUserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class UserService implements IUserService {
    @Setter
    @Getter
    private UserMapper userMapper;

    @Override
    public User findUser(User user) {
        return userMapper.findUser(user);
    }

    @Override
    public void insertOpenid(String openid) {
        userMapper.insertOpenid(openid);
    }

    @Override
    public void updateOpenid(User user) {
        userMapper.updateOpenid(user);
    }
}
