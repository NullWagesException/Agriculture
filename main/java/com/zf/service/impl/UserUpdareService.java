package com.zf.service.impl;

import com.zf.mapper.UpdateUserMapper;
import com.zf.pojo.UpdateUser;
import com.zf.service.IUserUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class UserUpdareService implements IUserUpdateService {

    @Autowired
    private UpdateUserMapper userMapper;

    public void setUserMapper(UpdateUserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UpdateUser get(Integer id) {
        return userMapper.get(id);
    }

    @Override
    public List<UpdateUser> getAll() {
        return userMapper.getAll();
    }

    @Override
    public void insert(UpdateUser update) {
        userMapper.insert(update);
    }

    @Override
    public void update(UpdateUser update) {
        userMapper.update(update);
    }

    @Override
    public void delete(Integer id) {
        userMapper.delete(id);
    }

    @Override
    public List<UpdateUser> getAllow(Integer allow) {
        return userMapper.getAllow(allow);
    }
}
