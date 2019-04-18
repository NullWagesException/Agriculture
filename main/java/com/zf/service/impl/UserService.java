package com.zf.service.impl;

import com.zf.mapper.UserMapper;
import com.zf.pojo.User;
import com.zf.service.IUserService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class UserService implements IUserService {
    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User findUser(User user) {
        return userMapper.findUser(user);
    }

    @Override
    public User get(Integer id) {
        return userMapper.get(id);
    }

    @Override
    public void updateOpenid(User user) {
        userMapper.updateOpenid(user);
    }

    @Override
    public List<User> getAll() {
        return userMapper.getAll();
    }

    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void delete(Integer id) {
        userMapper.delete(id);
    }
}
