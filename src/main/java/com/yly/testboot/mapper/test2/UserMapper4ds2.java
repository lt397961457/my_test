package com.yly.testboot.mapper.test2;

import com.yly.testboot.entity.UserEntity;

import java.util.List;

public interface UserMapper4ds2 {
    public UserEntity getOne(Integer id);
    public List<UserEntity> getAll();
}
